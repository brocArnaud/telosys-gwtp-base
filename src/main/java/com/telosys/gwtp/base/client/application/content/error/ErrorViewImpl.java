package com.telosys.gwtp.base.client.application.content.error;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter.ErrorView;

public class ErrorViewImpl extends ViewImpl implements ErrorView {
	interface Binder extends UiBinder<Widget, ErrorViewImpl> {
	}

	@Inject
	ErrorViewImpl(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}