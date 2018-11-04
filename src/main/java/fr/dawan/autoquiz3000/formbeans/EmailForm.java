package fr.dawan.autoquiz3000.formbeans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmailForm {
	@NotEmpty(message = "L'adresse du server SMTP ne peut pas être vide")
	@Pattern(regexp="^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$", message="L\'adresse n'est pas valide")
	private  String smtpServer;
	@NotEmpty(message = "L'email ne peut pas être vide")
	@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="L\'email n'est pas valide")
	private String emailSender;
	@NotNull(message = "Le port de pas être vide")
	@Min(0)
	@Max(65535)
	private Integer port;
	@NotEmpty(message = "L'identifaint ne peut pas être vide")
	private  String emailUser;
	@NotEmpty(message = "Le mot de passe peut pas être vide")
	private  String emailPassword;

	public EmailForm(String smtpServer,  String emailSender, Integer port, String emailUser, String emailPassword) {
		this.smtpServer = smtpServer;
		this.emailSender = emailSender;
		this.port = port;
		this.emailUser = emailUser;
		this.emailPassword = emailPassword;
	}

	public EmailForm() {

	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
