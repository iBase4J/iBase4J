package org.ibase4j.core.support;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件引擎
 * @author ShenHuaJie
 * @version $Id: MailEntrance.java, v 0.1 2014年12月4日 下午8:34:48 ShenHuaJie Exp $
 */
public class EmailEngine {
    private Logger              logger        = LoggerFactory.getLogger(EmailEngine.class);

    private MimeMessage         mimeMsg;                                                    // MIME邮件对象
    private Session             session;                                                    // 邮件会话对象
    private Properties          props;                                                      // 系统属性

    private String              username      = "";                                         // smtp认证用户名和密码
    private String              password      = "";

    private Multipart           mp;                                                         // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    private static final String SET_HOST      = "设置系统属性：mail.smtp.host =";
    private static final String PRE_TALK      = "准备获取邮件会话对象！";
    private static final String ERROR_TALK    = "获取邮件会话对象时发生错误！";
    private static final String PRE_MIME      = "准备创建MIME邮件对象！";
    private static final String ERROR_MIME    = "创建MIME邮件对象失败！";
    private static final String SET_AUTH      = "设置smtp身份认证：mail.smtp.auth = ";
    private static final String SET_SUBJECT   = "设置邮件主题！";
    private static final String ERROR_SUBJECT = "设置邮件主题发生错误！";
    private static final String ERROR_BODY    = "设置邮件正文时发生错误！";
    private static final String ADD_ATTEND    = "增加邮件附件：";
    private static final String SET_FROM      = "设置发信人！";
    private static final String SENDING       = "正在发送邮件....";
    private static final String SEND_SUCC     = "发送邮件成功！";
    private static final String SEND_ERR      = "邮件发送失败！";

    public EmailEngine() {
        createMimeMessage();
    }

    public EmailEngine(String smtp) {
        try {
            setSmtpHost(smtp);
            createMimeMessage();
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    /**
     * 设置SMTP主机
     * @param hostName String
     */
    public void setSmtpHost(String hostName) {
        logger.info(SET_HOST + hostName);
        if (props == null)
            props = System.getProperties(); // 获得系统属性对象

        props.put("mail.smtp.host", hostName); // 设置SMTP主机
        //props.put("mail.smtp.port", "995");
        //props.put("")
    }

    /**
     * 创建MIME邮件对象
     * @return boolean
     */
    public boolean createMimeMessage() {
        try {
            logger.info(PRE_TALK);
            session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
        } catch (Exception e) {
            logger.error(ERROR_TALK + e.getLocalizedMessage());
            return false;
        }
        logger.info(PRE_MIME);

        try {
            mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
            mp = new MimeMultipart();
            return true;
        } catch (Exception e) {
            logger.error(ERROR_MIME + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 
     * @param need boolean
     */
    public void setNeedAuth(boolean need) {
        logger.info(SET_AUTH + need);
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    /**
     * 
     * @param name String
     * @param pass String
     */
    public void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }

    /**
     * 设置主题
     * @param mailSubject String
     * @return boolean
     * 
     */
    public boolean setSubject(String mailSubject) {
        logger.info(SET_SUBJECT);
        try {
            mimeMsg.setSubject(mailSubject, "UTF-8");
            return true;
        } catch (Exception e) {
            logger.error(ERROR_SUBJECT);
            return false;
        }
    }

    /**
     * 设置内容
     * @param mailBody String
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + mailBody, "text/html;charset=UTF-8");
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            logger.error(ERROR_BODY + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 设置附件
     * @param name String
     * @param pass String
     */
    public boolean addFileAffix(String filename) {
        logger.info(ADD_ATTEND + filename);
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(MimeUtility.encodeText(fileds.getName()));
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            logger.error(ADD_ATTEND + filename + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 设置发信人
     * @param name  String
     * @param pass String
     */
    public boolean setFrom(String from) {
        logger.info(SET_FROM);
        try {
            String[] f = from.split(",");
            if (f.length > 1) {
                from = MimeUtility.encodeText(f[0]) + "<" + f[1] + ">";
            }
            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 设置收信人
     * @param name String
     * @param pass String
     */
    public boolean setTo(String to) {
        if (to == null)
            return false;

        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 设置抄送人
     * @param name String
     * @param pass String
     */
    public boolean setCopyTo(String copyto) {
        if (copyto == null)
            return false;

        try {
            mimeMsg.setRecipients(Message.RecipientType.CC,
                (Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 发送邮件
     * @param name String
     * @param pass String
     */
    public boolean sendout() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();

            logger.info(SENDING);

            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
            // 设置发送日期
            mimeMsg.setSentDate(new Date());
            // 发送
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            if (mimeMsg.getRecipients(Message.RecipientType.CC) != null) {
                transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
            }
            logger.info(SEND_SUCC);
            transport.close();
            return true;
        } catch (Exception e) {
            logger.error(SEND_ERR + e.getLocalizedMessage());
            return false;
        }
    }
}
