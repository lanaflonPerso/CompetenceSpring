package fr.dawan.autoquiz3000.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public final class EmailTools {

	private static volatile EmailTools instance = null;

	private String smtpServer;
	private int port;
	private String emailSender;
	private String user;
	private String password;

	private EmailTools() {

	}

	public static EmailTools getInstance() {
		if (instance == null) {
			instance = new EmailTools();
			instance.loadProperty();
		}
		return instance;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public void loadProperty() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			input = new FileInputStream(rootPath + "email_config.properties");
			prop.load(input);
			smtpServer = prop.getProperty("smtpServer");
			port = Integer.parseInt(prop.getProperty("smtpPort"));
			emailSender = prop.getProperty("emailSender");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveProperty() {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println(rootPath);
			output = new FileOutputStream(rootPath + "email_config.properties");
			prop.setProperty("smtpServer", smtpServer);
			prop.setProperty("smtpPort", String.valueOf(port));
			prop.setProperty("emailSender", emailSender);
			prop.setProperty("user", user);
			prop.setProperty("password", password);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void send(String to, String subject, String msg) throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(smtpServer);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(user, password));
		email.setSSLOnConnect(true);
		email.setFrom(emailSender);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(to);
		email.send();
	}
}
