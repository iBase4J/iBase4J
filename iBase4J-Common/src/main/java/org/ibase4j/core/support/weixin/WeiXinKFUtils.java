package org.ibase4j.core.support.weixin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.ibase4j.core.support.weixin.model.KeFu;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by jonson.xu on 15-5-18.
 */
public class WeiXinKFUtils {
    /**
     * 添加客服帐号
     *
     * @param keFu
     * @return
     */
    public static boolean insertKfAccount(KeFu keFu) {
        boolean isOk = false;
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + token;
            try {
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                String kfAccountString = JSONObject.toJSONString(keFu);
                httpsURLConnection.setRequestProperty("Content-length", String.valueOf(kfAccountString.length()));
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.write(kfAccountString.getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
                StringBuffer stringBuffer = new StringBuffer();
                int inputByte = dataInputStream.read();
                while (inputByte != -1) {
                    stringBuffer.append((char) inputByte);
                    inputByte = dataInputStream.read();
                }
                String kfString = stringBuffer.toString();
                JSONObject jsonObject = JSON.parseObject(kfString);
                if (jsonObject.containsKey("errcode")) {
                    int errcode = jsonObject.getIntValue("errcode");
                    if (errcode == 0) {
                        isOk = true;
                    } else {
                        //TODO 添加客服账号失败
                        isOk = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 修改客服帐号
     *
     * @param keFu
     * @return
     */
    public static boolean updateKfAccount(KeFu keFu) {
        boolean isOk = false;
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + token;
            try {
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                String kfAccountString = JSONObject.toJSONString(keFu);
                httpsURLConnection.setRequestProperty("Content-length", String.valueOf(kfAccountString.length()));
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.write(kfAccountString.getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
                StringBuffer stringBuffer = new StringBuffer();
                int inputByte = dataInputStream.read();
                while (inputByte != -1) {
                    stringBuffer.append((char) inputByte);
                    inputByte = dataInputStream.read();
                }
                String kfString = stringBuffer.toString();
                JSONObject jsonObject = JSON.parseObject(kfString);
                if (jsonObject.containsKey("errcode")) {
                    int errcode = jsonObject.getIntValue("errcode");
                    if (errcode == 0) {
                        isOk = true;
                    } else {
                        //TODO 添加客服账号失败
                        isOk = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 删除客服帐号
     *
     * @param keFu
     * @return
     */
    public static boolean deleteKfAccount(KeFu keFu) {
        boolean isOk = false;
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + token;
            try {
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                String kfAccountString = JSONObject.toJSONString(keFu);
                httpsURLConnection.setRequestProperty("Content-length", String.valueOf(kfAccountString.length()));
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.write(kfAccountString.getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
                StringBuffer stringBuffer = new StringBuffer();
                int inputByte = dataInputStream.read();
                while (inputByte != -1) {
                    stringBuffer.append((char) inputByte);
                    inputByte = dataInputStream.read();
                }
                String kfString = stringBuffer.toString();
                JSONObject jsonObject = JSON.parseObject(kfString);
                if (jsonObject.containsKey("errcode")) {
                    int errcode = jsonObject.getIntValue("errcode");
                    if (errcode == 0) {
                        isOk = true;
                    } else {
                        //TODO 添加客服账号失败
                        isOk = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 设置客服帐号头像
     *
     * @param keFuAccount
     * @param request
     * @return
     */
    public static boolean setKfAccountAvart(String keFuAccount, HttpServletRequest request) {
        boolean isOk = false;
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=" +
                    token + "&kf_account=" + keFuAccount;
            try {
                String filename = null;
                MultipartFile multipartFile = null;
                File file = null;
                String pathDir = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/upload");
                CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                        request.getSession().getServletContext());
                if (multipartResolver.isMultipart(request)) {
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    Iterator<String> iterator = multiRequest.getFileNames();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        multipartFile = multiRequest.getFile(key);
                    }
                }
                if (multipartFile != null) {
                    String boundary = request.getContentType().split("boundary=")[1];
                    filename = multipartFile.getOriginalFilename();
                    file = new File(pathDir + "/" + filename);
                    multipartFile.transferTo(file);
                    URL url = new URL(urlString);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                    httpURLConnection.setRequestProperty("Content-Type", request.getContentType());
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("--");
                    stringBuilder.append(boundary);
                    stringBuilder.append("\r\n");
                    stringBuilder.append("Content-Disposition: form-data; name=\"media\"; filename=\"" + filename + "\"");
                    stringBuilder.append("\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream");
                    stringBuilder.append("\r\n\r\n");
                    byte[] data = stringBuilder.toString().getBytes();
                    dataOutputStream.write(data);
                    DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = dataInputStream.read(bufferOut)) != -1) {
                        dataOutputStream.write(bufferOut, 0, bytes);
                    }
                    dataOutputStream.write(end_data);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    dataInputStream.close();
                    dataInputStream = new DataInputStream(httpURLConnection.getInputStream());
                    int b = dataInputStream.read();
                    StringBuffer stringBuffer = new StringBuffer();
                    while (b != -1) {
                        stringBuffer.append((char) b);
                        b = dataInputStream.read();
                    }
                    String line = stringBuffer.toString();
                    JSONObject jsonObject = JSON.parseObject(line);
                    if (jsonObject.containsKey("errcode")) {
                        int errcode = jsonObject.getIntValue("errcode");
                        if (errcode == 0) {
                            isOk = true;
                        } else {
                            //TODO 添加客服账号失败
                            isOk = false;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 获取所有客服帐号
     *
     * @return
     */
    public static String getAllKfAccount() {
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + token;
            try {
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setDoInput(true);
                DataInputStream dataInputStream = new DataInputStream(httpsURLConnection.getInputStream());
                StringBuffer stringBuffer = new StringBuffer();
                int inputByte = dataInputStream.read();
                while (inputByte != -1) {
                    stringBuffer.append((char) inputByte);
                    inputByte = dataInputStream.read();
                }
                String kfString = stringBuffer.toString();
                return kfString;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
