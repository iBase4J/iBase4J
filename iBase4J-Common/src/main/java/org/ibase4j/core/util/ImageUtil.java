package org.ibase4j.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片处理辅助类
 * 
 * @author ShenHuaJie
 * @since 2012-03-21
 */
public class ImageUtil {
	private ImageUtil() {
	}

	/**
	 * * 转换图片大小，不变形
	 * 
	 * @param img 图片文件
	 * @param width 图片宽
	 * @param height 图片高
	 */
	public static void changeImge(File img, int width, int height) {
		try {
			Thumbnails.of(img).size(width, height).keepAspectRatio(false).toFile(img);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("图片转换出错！", e);
		}
	}

	/**
	 * 根据比例缩放图片
	 * 
	 * @param orgImgFile 源图片路径
	 * @param scale 比例
	 * @param targetFile 缩放后的图片存放路径
	 * @throws IOException
	 */
	public static void scale(BufferedImage orgImg, double scale, String targetFile) throws IOException {
		Thumbnails.of(orgImg).scale(scale).toFile(targetFile);
	}

	public static void scale(String orgImgFile, double scale, String targetFile) throws IOException {
		Thumbnails.of(orgImgFile).scale(scale).toFile(targetFile);
	}

	/**
	 * 图片格式转换
	 * 
	 * @param orgImgFile
	 * @param width
	 * @param height
	 * @param suffixName
	 * @param targetFile
	 * @throws IOException
	 */
	public static void format(String orgImgFile, int width, int height, String suffixName, String targetFile)
			throws IOException {
		Thumbnails.of(orgImgFile).size(width, height).outputFormat(suffixName).toFile(targetFile);
	}

	/**
	 * 根据宽度同比缩放
	 * 
	 * @param orgImg 源图片
	 * @param orgWidth 原始宽度
	 * @param targetWidth 缩放后的宽度
	 * @param targetFile 缩放后的图片存放路径
	 * @throws IOException
	 */
	public static double scaleWidth(BufferedImage orgImg, int targetWidth, String targetFile) throws IOException {
		int orgWidth = orgImg.getWidth();
		// 计算宽度的缩放比例
		double scale = targetWidth * 1.00 / orgWidth;
		// 裁剪
		scale(orgImg, scale, targetFile);

		return scale;
	}

	public static void scaleWidth(String orgImgFile, int targetWidth, String targetFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
		scaleWidth(bufferedImage, targetWidth, targetFile);
	}

	/**
	 * 根据高度同比缩放
	 * 
	 * @param orgImgFile //源图片
	 * @param orgHeight //原始高度
	 * @param targetHeight //缩放后的高度
	 * @param targetFile //缩放后的图片存放地址
	 * @throws IOException
	 */
	public static double scaleHeight(BufferedImage orgImg, int targetHeight, String targetFile) throws IOException {
		int orgHeight = orgImg.getHeight();
		double scale = targetHeight * 1.00 / orgHeight;
		scale(orgImg, scale, targetFile);
		return scale;
	}

	public static void scaleHeight(String orgImgFile, int targetHeight, String targetFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
		// int height = bufferedImage.getHeight();
		scaleHeight(bufferedImage, targetHeight, targetFile);
	}

	// 原始比例缩放
	public static void scaleWidth(File file, Integer width) throws IOException {
		String fileName = file.getName();
		String filePath = file.getAbsolutePath();
		String postFix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		// 缩放
		BufferedImage bufferedImg = ImageIO.read(file);
		String targetFile = filePath + "_s" + postFix;
		scaleWidth(bufferedImg, width, targetFile);
		String targetFile2 = filePath + "@" + width;
		new File(targetFile).renameTo(new File(targetFile2));
	}
}
