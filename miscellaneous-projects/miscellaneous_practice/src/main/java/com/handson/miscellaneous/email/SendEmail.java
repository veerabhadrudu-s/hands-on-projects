package com.handson.miscellaneous.email;

/**
 * @author sveera
 *
 */
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void main(String[] args) {
		String host = "localhost";
		try (Scanner inputDevice = new Scanner(System.in)) {
			System.out.print("Enter the destination / to email address : ");
			String to = inputDevice.nextLine();
			System.out.print("Enter the source / from  email address : ");
			String from = inputDevice.nextLine();
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			System.out.print("Enter the email Subject : ");
			message.setSubject(inputDevice.nextLine());
			System.out.print("Enter the email Body : ");
			message.setText(inputDevice.nextLine());
			Transport.send(message);
			System.out.println("message sent successfully....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
