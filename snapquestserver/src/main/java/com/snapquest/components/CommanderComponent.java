package com.snapquest.components;

import org.mule.api.MuleEventContext;
import org.mule.api.annotations.param.Payload;
import org.mule.api.lifecycle.Callable;

public class CommanderComponent {
	
	public static Object consumeMessage(Object inData) {
		String data = (String) inData;
		if(data.contains("session")){
			Commander.consume(data);
		}
		return "";
	}
}
