package org.ibase4j.core.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.Cell;
import jxl.CellType;
import jxl.CellView;
import jxl.Range;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.BoldStyle;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 下载EXCEL文件
 * 
 * @author ShenHuaJie
 * @since 2011-11-08
 */
@SuppressWarnings("deprecation")
public class DownloadExcelUtil {
    private static final Log   log        = LogFactory.getLog(DownloadExcelUtil.class);

    private Integer            icol       = 0;                                       // 记录列
    private Integer            irow       = -1;                                      // 记录行
    private OutputStream       os;                                                   // 获得输出流
    private WritableWorkbook   wbook;                                                // 创建excel文件
    private WritableSheet      wsheet;                                               // 工作表
    private WritableCellFormat wcfFC;                                                // 单元格样式
    private WritableFont       wfont;                                                // 字体样式

    private Integer            trow       = -1;
    private Integer            titleCols  = 0;
    private long               startTime;
    private int                sheetIndex = 0;
    private String             sheetName;

    /**
     * 设置文件名和工作表名(Excel)
     * 
     * @param response
     *            为NULL时，写入磁盘
     * @param fileName
     *            文件名
     * @param sheetName
     *            工作表名
     * @throws IOException
     */
    public DownloadExcelUtil(HttpServletResponse response, String fileName, String sheetName)
                                                                                           throws IOException {
        startTime = System.currentTimeMillis();
        if (fileName.indexOf(".xls") < 0) {
            fileName += ".xls";
        }
        if (response != null && response instanceof HttpServletResponse) {
            log.warn("Write Excel To Memory.Please wait...");
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));

            os = response.getOutputStream();// 获得输出流
            os.flush();
            wbook = Workbook.createWorkbook(os); // 创建excel文件
        } else {
            log.warn("Write Excel To Disk.Please wait...");
            wbook = Workbook.createWorkbook(new File(fileName)); // 创建excel文件
        }
        this.sheetName = sheetName;
        wsheet = wbook.createSheet(sheetName, sheetIndex++); // sheet名称
    }

    public void addSheet(String sheetName) {
        irow = -1;
        this.sheetName = sheetName;
        wsheet = wbook.createSheet(sheetName, sheetIndex++);
    }

    /**
     * 设置报表标题
     * 
     * @param reportTitle
     *            报表标题
     * @throws IOException
     * @throws WriteException
     * @throws WriteException
     */
    public void setReportTitle(String reportTitle) throws WriteException, IOException {
        try {
            irow++;
            wfont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            wcfFC = new WritableCellFormat(wfont);
            wcfFC.setAlignment(Alignment.CENTRE);// 对齐方式
            // wcfFC.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);// 背景色
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 对齐方式
            // wcfFC.setBorder(Border.ALL, BorderLineStyle.MEDIUM,
            // Colour.BLACK);//
            // 边框
            wsheet.addCell(new Label(icol, irow, reportTitle, wcfFC));
            trow = irow;
        } catch (Exception e) {
            this.close();
        }
    }

    /**
     * 设置报表内容头
     * 
     * @param listTitle
     *            报表头
     * @throws IOException
     * @throws WriteException
     */
    @Deprecated
    public void setExcelListTitle(String[] listTitle) throws WriteException, IOException {
        try {
            irow++;
            long start = System.currentTimeMillis();
            wfont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            wcfFC = new WritableCellFormat(wfont);
            wcfFC.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            wcfFC.setAlignment(Alignment.CENTRE);// 对齐方式
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 对齐方式
            for (int i = icol; i < listTitle.length; i++) {
                wsheet.addCell(new Label(i, irow, listTitle[i], wcfFC));
            }
            trow = irow;
            log.info("title use time:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            this.close();
        }
    }

    /**
     * 添加一行
     * 
     * @param strings
     *            该行数据
     * @throws IOException
     * @throws WriteException
     */
    public void addRow(Object[] strings, BorderLineStyle borderLineStyle, Alignment alignment,
                       String bold) throws WriteException, IOException {
        try {
            irow++;
            bold = StringUtils.isEmpty(bold) ? "" : bold;
            for (int i = 0; i < strings.length; i++) {
                if ("bold".equals(bold.toLowerCase()))
                    wfont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD,
                        false);
                else
                    wfont = new WritableFont(WritableFont.createFont("宋体"), 10,
                        WritableFont.NO_BOLD, false);
                wcfFC = new WritableCellFormat(wfont);
                wcfFC.setAlignment(alignment);// 对齐方式
                wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 对齐方式
                if (borderLineStyle == BorderLineStyle.THIN && i == strings.length - 1) {
                    wcfFC.setBorder(Border.ALL, borderLineStyle);
                    wcfFC.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
                } else
                    wcfFC.setBorder(Border.ALL, borderLineStyle);
                wsheet.addCell(new Label(i, irow, strings[i] == null ? "" : strings[i].toString(),
                    wcfFC));
            }
        } catch (Exception e) {
            log.error(e);
            this.close();
        }
    }

    /**
     * 添加一行
     * 
     * @param strings
     *            该行数据
     * @throws IOException
     * @throws WriteException
     */
    public void addRow(Object[] strings, CellType[] cellTypes, DisplayFormat... dFormat)
                                                                                        throws WriteException,
                                                                                        IOException {
        try {
            irow++;
            DisplayFormat format = null;
            for (int i = 0; i < strings.length; i++) {
                if (dFormat != null) {
                    if (dFormat.length > i) {
                        format = dFormat[i];
                    } else if (dFormat.length > 0) {
                        format = dFormat[0];
                    }
                }
                addCell(i, irow, strings[i] == null ? "" : strings[i].toString(), cellTypes[i],
                    format, false, i + 1 == strings.length);
            }
        } catch (Exception e) {
            log.error(e);
            this.close();
        }
    }

    /**
     * 添加多行
     * 
     * @param infoList
     *            报表内容
     * @param cellTypes
     *            单元格样式
     * @throws IOException
     * @throws WriteException
     * @throws Exception
     */
    public void addRows(List<?> infoList, CellType[] cellTypes, DisplayFormat... dFormat)
                                                                                         throws WriteException,
                                                                                         IOException {
        if (infoList != null && !infoList.isEmpty()) {
            // 内容
            CellType cellType = CellType.EMPTY;
            DisplayFormat format = null;
            for (; 0 < infoList.size();) {
                if (irow == 50000) {
                    this.write();
                    this.addSheet(sheetName);
                }
                irow++;
                Object[] rowInfo = (Object[]) infoList.get(0);
                if (rowInfo.length > titleCols) {
                    titleCols = rowInfo.length;
                }
                for (int j = icol; j < rowInfo.length; j++) {
                    rowInfo[j] = rowInfo[j] == null ? "" : rowInfo[j];
                    if (cellTypes != null && j < cellTypes.length) {
                        cellType = cellTypes[j] == null ? CellType.EMPTY : cellTypes[j];
                    } else {
                        cellType = CellType.EMPTY;
                    }
                    if (dFormat != null) {
                        if (dFormat.length > j) {
                            format = dFormat[j];
                        } else if (dFormat.length > 0) {
                            format = dFormat[0];
                        }
                    }
                    this.addCell(j, irow, rowInfo[j], cellType, format, 1 == infoList.size(),
                        j == rowInfo.length - 1);// 添加单元格并判断是否为最后一列最后一行
                }
                infoList.remove(0);
            }
            try {
                if (os != null)
                    os.flush();
                if (trow >= 0)
                    wsheet.mergeCells(icol, trow, titleCols + icol - 1, trow);// 设置报表标题
            } catch (Exception e) {
                log.error(e);
                this.close();
            }
        }
    }

    /**
     * 下载Excel
     * 
     * @throws IOException
     * @throws WriteException
     * @throws Exception
     */
    public void reportExcel() throws WriteException, IOException {
        log.info("Use time:" + MathUtil.divide(System.currentTimeMillis() - startTime, 1000) + "s");
        this.flush();
        log.info("ReportExcel Successful!!!");
    }

    /**
     * 合并单元格
     * 
     * @param col
     *            起始列
     * @param row
     *            起始行
     * @param toCol
     *            结束列
     * @param toRow
     *            结束行
     * @throws IOException
     * @throws WriteException
     * @throws Exception
     */
    public void setMergeCells(int col, int row, int toCol, int toRow) throws WriteException,
                                                                     IOException {
        try {
            wsheet.mergeCells(col, row, toCol, toRow);
        } catch (Exception e) {
            this.close();
        }
    }

    /**
     * 关闭资源
     * 
     * @throws WriteException
     * @throws IOException
     */
    public void close() throws WriteException, IOException {
        if (wbook != null) {
            wbook.write();
            wbook.close();
        }
        if (os != null) {
            os.flush();
            os.close();
        }
    }

    /**
     * 关闭资源
     * 
     * @throws WriteException
     * @throws IOException
     */
    public void write() throws WriteException, IOException {
        this.setRowView();
        this.setColumnView();
        if (os != null) {
            os.flush();
        }
    }

    /**
     * 释放资源
     * 
     * @throws WriteException
     * @throws IOException
     */
    private void flush() throws WriteException, IOException {
        this.setRowView();
        this.setColumnView();
        this.close();
    }

    /**
     * 释放资源
     * 
     * @throws IOException
     * @throws WriteException
     */
    public void osFlush() throws IOException, WriteException {
        if (os != null) {
            os.flush();
        }
    }

    /**
     * 添加单元格
     * 
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public void addCell(Integer col, Integer row, Object o, CellType type, DisplayFormat format,
                        Boolean isLastRow, Boolean isLastCols) throws WriteException, IOException {
        WritableFont wfont = new WritableFont(WritableFont.createFont("宋体"), 10,
            WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        try {
            if (o instanceof ArrayList<?>) {
                Label Label = new Label(col, row, "", wcfFC);
                WritableCellFeatures wcf = new WritableCellFeatures();
                if (!((List<?>) o).isEmpty())
                    wcf.setDataValidationList((List<?>) o);
                Label.setCellFeatures(wcf);
                wsheet.addCell(Label);
            } else {
                // 字体样式
                if (type == CellType.LABEL) {
                    wfont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD,
                        false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
                    wcfFC = new WritableCellFormat(wfont);
                    wcfFC.setAlignment(Alignment.CENTRE);// 对齐方式
                } else if (type == CellType.STRING_FORMULA) {
                    wcfFC = new WritableCellFormat(wfont);
                    wcfFC.setAlignment(Alignment.LEFT);// 对齐方式
                } else if (type == CellType.NUMBER) {// 数字
                    wcfFC = new WritableCellFormat(wfont, format);
                    wcfFC.setAlignment(Alignment.RIGHT);// 对齐方式
                } else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
                    wcfFC = new jxl.write.WritableCellFormat(wfont, format);
                    wcfFC.setAlignment(Alignment.CENTRE);// 对齐方式
                } else {
                    wcfFC = new WritableCellFormat(wfont);
                    wcfFC.setAlignment(Alignment.CENTRE);// 对齐方式
                }
                wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 对齐方式
                wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
                if (isLastCols) {
                    wcfFC.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM);
                }
                if (isLastRow) {
                    wcfFC.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
                }
                if (o == null) {
                    wsheet.addCell(new Label(col, row, ""));
                } else if (StringUtils.isEmpty(String.valueOf(o))) {
                    wsheet.addCell(new Label(col, row, o.toString(), wcfFC));
                } else if (type == CellType.NUMBER) {
                    wsheet.addCell(new jxl.write.Number(col, row,
                        Double.valueOf(String.valueOf(o)), wcfFC));
                } else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {
                    wsheet.addCell(new jxl.write.DateTime(col, row, DateUtil.stringToDate(o
                        .toString()), wcfFC));
                } else {
                    wsheet.addCell(new Label(col, row, o.toString(), wcfFC));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
        }
    }

    /**
     * 设置行高
     * 
     * @throws IOException
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void setRowView() throws WriteException, IOException {
        try {
            for (int i = 0; i < wsheet.getRows(); i++) {
                wsheet.setRowView(i, (int) (wsheet.getRowView(i).getDimension() * 1.3));
            }
        } catch (Exception e) {
            this.close();
        }
    }

    /**
     * 设置列宽
     * 
     * @param cellInfo
     * @param col
     */
    private void setColumnView() {
        int infoWidth, cellWidth;
        String value;
        Pattern pattern = Pattern.compile("\\d+(.\\d+)?$");
        Cell cell;
        for (int i = 0; i < wsheet.getRows(); i++) {
            lablea: for (int j = 0; j < wsheet.getColumns(); j++) {
                // 过滤合并单元格
                Range[] range = wsheet.getMergedCells();
                for (int k = 0; k < range.length; k++) {
                    if (range[k].getTopLeft().getRow() == i
                        && range[k].getTopLeft().getColumn() == j
                        && range[k].getBottomRight().getColumn() != j)
                        continue lablea;
                }
                cell = wsheet.getCell(j, i);
                value = cell.getContents();
                if (cell.getType() == CellType.DATE) {// 日期
                    infoWidth = (int) Math.round(value.length() * 0.5);
                } else if (cell.getType() == CellType.NUMBER) {// 数字
                    int p = 0;
                    for (int k = 0; k < value.split("\\.")[0].length(); k++) {
                        if (value.charAt(k) == '0') {
                            p++;
                        }
                    }
                    infoWidth = (int) Math.round(value.length() * 2 + p * 0.2);
                } else if (pattern.matcher(value).matches()) {// 数字
                    infoWidth = (int) Math.round(value.length() * 1.2);
                } else if (cell.getCellFormat() != null
                           && cell.getCellFormat().getFont().getBoldWeight() == BoldStyle.BOLD
                               .getValue()) {// 粗体
                    infoWidth = (int) Math.round(value.getBytes().length * 1.13);
                } else if (value.getBytes().length != value.length()) {
                    infoWidth = (int) Math.round(value.length() * 1.9);
                } else {
                    infoWidth = (int) Math.round(value.length() * 1.05);
                }
                cellWidth = wsheet.getColumnView(j).getDimension();
                if (cellWidth < infoWidth) {
                    wsheet.setColumnView(j, infoWidth);
                }
            }
        }
    }

    /** 隐藏列 */
    public void setHideCol(int rols) {
        CellView view = new CellView();
        view.setHidden(true);
        wsheet.setColumnView(rols, view);
    }

    /** 隐藏行 */
    public void setHideRow(int row) throws RowsExceededException {
        CellView view = new CellView();
        view.setHidden(true);
        wsheet.setRowView(row, view);
    }

    /** 删除列 */
    public void deleteCol(int rols) {
        wsheet.removeColumn(rols);
    }

    /** 删除行 */
    public void deleteRow(int row) {
        wsheet.removeRow(row);
    }

    public void setIrow(Integer row) {
        this.irow = row;
    }

    public int getIrow() {
        return this.irow;
    }

    public void setIcol(Integer col) {
        icol = col;
    }

    public Integer getIcol() {
        return icol;
    }

    public Integer getTitleCols() {
        return titleCols;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }
}
