//package com.zarek.itrip.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.internet.MimeMessage;
//
///**
// * <b>邮件发送工具类</b>
// * @author Arthur
// * @version 1.0.0
// * @since 1.0.0
// */
//@Component("mailUtil")
//public class MailUtil {
//	@Autowired
//	private JavaMailSender mailSender;
//
//	/**
//	 * <b>通过邮件发送激活码信息</b>
//	 * @param email
//	 * @param code
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean sendActivationCodeMail(String email, String code) throws Exception {
//		// 获得可以设定 HTML 格式的邮件对象
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//		// 设置发送人邮件地址
//		messageHelper.setFrom("ekgcitrip@163.com");
//		// 设定收件人地址
//		messageHelper.setTo(email);
//		// 设定抄送人地址
//		messageHelper.setCc("ekgcitrip@163.com");
//		// 设定所发送邮件的主题
//		messageHelper.setSubject("爱旅行用户注册激活码");
//		// 设置所发送邮件的内容
//		messageHelper.setText(code, true);
//		// 发送邮件
//		mailSender.send(mimeMessage);
//
//		return true;
//	}
//}
