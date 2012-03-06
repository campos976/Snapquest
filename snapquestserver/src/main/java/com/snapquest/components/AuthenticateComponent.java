package com.snapquest.components;

import org.mule.api.MuleEventContext;
import org.mule.api.annotations.param.Payload;
import org.mule.api.lifecycle.Callable;

public class AuthenticateComponent {

	public static Object consumeMessage(Object inData) {
		System.out.print("Got it: byte array");
		String data = (String) inData;
		System.out.println("ECHO: " + data);
		String sessionKey = Authenticate.authenticate(data);
		return sessionKey;
	}
}
