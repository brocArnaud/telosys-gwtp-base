package com.telosys.gwtp.base.client.util.view;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.PanelBody;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.uibinder.client.UiField;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class BaseFormView<C extends UiHandlers> extends ViewWithUiHandlers<C> {

	@Ignore
	@UiField
	public PanelBody body;

	@Ignore
	@UiField
	public Column notification;

	@Ignore
	@UiField
	public Button create;

	@Ignore
	@UiField
	public Label labelNotification;

}