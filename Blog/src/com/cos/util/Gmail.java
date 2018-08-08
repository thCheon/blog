package com.cos.util;

import javax.mail.*;

public class Gmail extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication("cheonth", "xogns123");
	}
}
