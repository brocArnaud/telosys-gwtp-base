package com.telosys.gwtp.base.client.util.view;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.PanelBody;

import com.google.gwt.uibinder.client.UiField;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class BaseFormView<C extends UiHandlers> extends ViewWithUiHandlers<C> {

	@UiField
	public PanelBody body;

	@UiField
	public Column notification;

	@UiField
	public Button create;

	@UiField
	public Label labelNotification;
}