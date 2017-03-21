package com.jn.Utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.Transport;

import org.springframework.stereotype.Component;
@Component
public class SendEmailUtil {
	public boolean send(String str,String strto){
		Transport transport = null;
	try{
		Properties props = new Properties();    
		props.put( "mail.smtp.host ", "smtp.163.com ");    
		props.put("mail.smtp.auth", "true");    
		Session session = Session.getInstance(props);   
		Message message = new MimeMessage(session);         
		InternetAddress from = new InternetAddress("18861824097@163.com");    
		from.setPersonal(MimeUtility.encodeText("Password<18861824097@163.com>"));    
		message.setFrom(from);         
		InternetAddress to = new InternetAddress(strto);    
		message.setRecipient(Message.RecipientType.TO, to);        
		message.setSubject(MimeUtility.encodeText("Password"));    
		message.setSentDate(new Date());            
		// 构建邮件内容对象    
		Multipart mm = new MimeMultipart();    
		// 构建一个消息内容块    
		BodyPart mbpFile = new MimeBodyPart(); 
		//background 防止当做垃圾邮件
		mbpFile.setContent("<body background=\"http://localhost:8080/Project1101/images/Email_bacground.jpg\">" 
				+"Your Password:"+ str + "</body>","text/html;charset=UTF-8");    
		mm.addBodyPart(mbpFile);   
		message.setContent(mm);   
		message.saveChanges();   
		transport = session.getTransport("smtp");    
		transport.connect("smtp.163.com", 25, "18861824097@163.com","h18861824097");    
		transport.sendMessage(message, message.getAllRecipients());   
		return true;
		}catch(Exception e){
			return false;
		}finally{
			try {
				transport.close();
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
	}
}
