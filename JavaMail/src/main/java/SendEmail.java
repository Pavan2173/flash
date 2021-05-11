import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void SendMail(String recepient) throws Exception
	{
	  Properties properties = new Properties();
	  
	  properties.put("mail.smtp.auth", "true");
	  properties.put("mail.smtp.starttls.enable", "true");
	  properties.put("mail.smtp.host","smtp.gamil.com");
	  properties.put("mail.smtp.port", "587");
	  
	  final String myAccountEmail = "iampavankalyan21@gmail.com";
	  final String password = "Pskalyan";
	  
	  Session session = Session.getInstance(properties, new Authenticator() {
		  @Override
		  protected PasswordAuthentication getPasswordAuthentication() 
		  {
			  return new PasswordAuthentication(myAccountEmail,password);
		  }  
	  });
	   Message message = prepareMessage(session,myAccountEmail,recepient);
	   
	   Transport.send(message);
	   System.out.println("Sent...");
	   System.out.println("Message sent successfully");
	}
	private static Message prepareMessage(Session session,String myAccountEmail,String recepient)
	{
		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("my First Email");
			message.setText("Welcome");
			return message;
		}
		 catch(Exception ex) 
		 {
			 Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE,null, ex);
			 
		 }
		return null;
	}

}
