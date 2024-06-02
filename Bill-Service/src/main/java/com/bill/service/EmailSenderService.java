package com.bill.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	    @Autowired
	    private JavaMailSender mailSender;

	    public void sendTextMail(String to, String subject, String body) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        mailSender.send(message);
	    }
	    
	    public void sendTextWithAttachment(String to, String subject, String body,String filePath,String orderId) throws MessagingException, IOException {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper=new MimeMessageHelper(message,true);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(body);
	        Path path=Paths.get(filePath);
	        Resource resource=new ByteArrayResource(Files.readAllBytes(path));
	        String fileName=path.getFileName().toString();
	        int dotIndex=fileName.indexOf('.');
	        String nameString=fileName.substring(0, dotIndex);
	        String extension=fileName.substring(dotIndex);
	        
	        fileName=nameString+"No:"+orderId+extension;
	        helper.addAttachment(fileName, resource);
	        mailSender.send(message);
	    }
	
}
