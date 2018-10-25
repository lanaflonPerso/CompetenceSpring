package fr.dawan.autoquiz3000.ctrl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class Ctrl {
	
	protected final Pattern VALID_DATE_REGEX= Pattern.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$", Pattern.CASE_INSENSITIVE);

	protected boolean error= false;

	//********************Getters / Setters******************
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	//********************Methods Utilitaires****************
	public static String MySQLPassword(String password) {
        byte[] utf8 = null;
		try {
			utf8 = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return "*" + DigestUtils.sha1Hex(DigestUtils.sha1(utf8)).toUpperCase();
	}
	
	protected Date StringToDate(String date) {
		Matcher matcher = VALID_DATE_REGEX.matcher(date);
		
		if(matcher.find()) {
			String[] tab= date.split("-");
			int day= Integer.valueOf(tab[2]);
			int month= Integer.valueOf(tab[1]);
			int year= Integer.valueOf(tab[0]);
			
			LocalDate localDate= LocalDate.of(year, month, day);
			return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return new Date();
	}
}
