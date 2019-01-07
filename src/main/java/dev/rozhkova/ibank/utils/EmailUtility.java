package dev.rozhkova.ibank.utils;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * A utility class for sending e-mail message with attachment.
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	
	/**
	 * Sends an e-mail message from a SMTP host with a list of attached files. 
	 * 
	 */
	public static void sendEmailWithAttachment(final String host, final String port,
                                               final String userName, final String password, final String toAddress,
                                               final String subject, final String message, final List<File> attachedFiles)
					throws MessagingException {
		// sets SMTP server properties
		final Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		final Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		final Session session = Session.getInstance(properties, auth);
		// creates a new e-mail message
		final Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
		final InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// creates message part
		final MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");
		// creates multi-part
		final Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// adds attachments
		if (attachedFiles != null && attachedFiles.size() > 0) {
			for (final File aFile : attachedFiles) {
				final MimeBodyPart attachPart = new MimeBodyPart();
				try {
					attachPart.attachFile(aFile);
				} catch (final IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}
		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);
	}
}