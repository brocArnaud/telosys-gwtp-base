package com.telosys.gwtp.base.client.util;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.dispatch.client.ExceptionHandler;

public class ErrorHandler implements ExceptionHandler {

	@Override
	public Status onFailure(Throwable e) {
		GWT.log("Error handled : " + e);
		return Status.STOP;
	}

}
