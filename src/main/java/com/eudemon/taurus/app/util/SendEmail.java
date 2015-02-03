package com.eudemon.taurus.app.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * �� 126 ���� ����
 */
public class SendEmail {
	private static Logger logger = Logger.getLogger(SendEmail.class);
	
    private boolean auth = true;// ��ʾ �Ƿ���Ҫ��֤
    private String from;// ˭����
    private String to;// ����˭��
    private String username;// �������û���
    private String password;// ����������
    private String title;// ����
    private String content;// ����
    public SendEmail(String from, String to, String username, String password,
                    String title, String content) {
        this.from = from;
        this.to = to;
        this.username = username;
        this.password = password;
        this.title = title;
        this.content = content;
    }
    public boolean send() {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "202.123.98.13");
        Session session = null;
        // �����Ƿ���Ҫ��֤ 126 ������Ҫ��֤ auth ����Ĭ����Ϊtrue
        if (auth) {
            props.put("mail.smtp.auth", "true");
            // ���� Authenticator ͨ���û�������������ܱ�������Դ
            Authenticator author = new MyAuthenticator(username, password);
            session = Session.getDefaultInstance(props, author);
        } else {
            props.put("mail.smtp.auth", "false");
            // null��ʾ��֤
            session = Session.getDefaultInstance(props, null);
        }
        // �����ڿͻ��� ��ʾ ��������Ϣ
        session.setDebug(true);
        // ��message
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            // ����ȥ��
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    to));
            message.setSubject(title);
            message.setText(content);
            // ��������
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            logger.error("send mail exception :��" + e.getMessage(), e);
        }
        props.remove("proxySet");
        props.remove("ProxyHost");
        props.remove("ProxyPort");
        return false;
    }
    private class MyAuthenticator extends Authenticator {
        private String username, password;
        public MyAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
//        SendEmail se = new SendEmail("wangqi@xinhua.cn", "7969660@qq.com", "wangqi@xinhua.cn", "xhnwq","����", "����");
//        SendEmail se = new SendEmail("norepl@home.news.cn", "7969660@qq.com", "norepl", "xinhua123","����", "����");
        SendEmail se = new SendEmail("noreply@home.news.cn", "wangqi@xinhua.cn", "noreply@home.news.cn", "66242229kwm","����", "����");
        se.send();
        System.out.println("�������");
    }
}