package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication("kevin018111@gmail.com", "kevin1141");

	}

}
