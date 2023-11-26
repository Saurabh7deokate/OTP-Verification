package pin.mailapi;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendMail {

	public static void main(String[] args) throws MessagingException {

		String from = "<Your-Email";
		String to = "<Receiver-Email>";

		Random random = new Random();
		Integer minLength = 100000;
		Integer maxLength = 999999;

		Integer otp = (int) (random.nextDouble() * (maxLength - minLength));

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				String userName = "<Your-Email>";
				String password = "<Your-Authentication-Password>";
				return new PasswordAuthentication(userName, password);
			}
		});
		try {
			session.setDebug(true);
			InternetAddress from1 = new InternetAddress(from);
			InternetAddress to1 = new InternetAddress(to);

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(from1);
			mimeMessage.addRecipient(RecipientType.TO, to1);
			mimeMessage.setSubject("OTP For Verification");
			mimeMessage.setText("OTP is: " + otp);
			Transport.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
