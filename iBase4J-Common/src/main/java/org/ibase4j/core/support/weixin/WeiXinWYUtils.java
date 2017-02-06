package org.ibase4j.core.support.weixin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.ibase4j.core.support.weixin.model.WXMessasgeTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 */
public class WeiXinWYUtils {
    /**
     * 报修处理进展通知
     */
    public static boolean progressNotic() {
        boolean isOk = false;
        String token = WeiXinUtils.getToken();
        if (token != null) {
            String urlString = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
            try {
//                op-51t-YG9RE_Pcfr9WD8e9MRYN0
                WXMessasgeTemplate wxMessasgeTemplate = new WXMessasgeTemplate();
//                wxMessasgeTemplate.setTouser("op-51t5m2L_VLsDvXXDdSm-BOToY");
                wxMessasgeTemplate.setTouser("op-51t-YG9RE_Pcfr9WD8e9MRYN0");
                wxMessasgeTemplate.setTemplate_id("7GabJuZ-w-0ZTKMdajfmXB3WBVCp55NAmnrUS_IqU3Y");
                wxMessasgeTemplate.setUrl("http://weixin.qq.com/download");
                JSONObject jsonObject = new JSONObject();
                JSONObject firstJsonObject = new JSONObject();
                firstJsonObject.put("value", "尊敬的耿adfasf：您的报修有新的进展。");
                jsonObject.put("first", firstJsonObject);

                JSONObject key1 = new JSONObject();
                key1.put("value", "郑汴路dfasfdf");
                jsonObject.put("keyword1", key1);

                JSONObject key2 = new JSONObject();
                key2.put("value", "郑汴路adfasf1");
                jsonObject.put("keyword2", key2);

                JSONObject key3 = new JSONObject();
                key3.put("value", "郑汴路2adfasf");
                jsonObject.put("keyword3", key3);

                JSONObject key4 = new JSONObject();
                key4.put("value", "郑汴路asdfasf3");
                jsonObject.put("keyword4", key4);

                JSONObject key5 = new JSONObject();
                key5.put("value", "郑汴路afdasf4");
                jsonObject.put("keyword5", key5);

                JSONObject remark = new JSONObject();
                remark.put("value", "3ks!");
                jsonObject.put("remark", remark);
                wxMessasgeTemplate.setData(jsonObject);
                URL url = new URL(urlString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                String kfAccountString = JSONObject.toJSONString(wxMessasgeTemplate);
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
                System.out.println(kfString);
                JSONObject jsonObject1 = JSON.parseObject(kfString);
                if (jsonObject1.containsKey("errcode")) {
                    int errcode = jsonObject1.getIntValue("errcode");
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
}
