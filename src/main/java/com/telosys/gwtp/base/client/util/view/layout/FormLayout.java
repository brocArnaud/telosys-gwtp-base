package com.telosys.gwtp.base.client.util.view.layout;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.PanelBody;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FormLayout extends Composite {

	interface Binder extends UiBinder<Widget, FormLayout> {
	}

	@UiField
	public PanelBody body;

	@UiField
	public Column notification;

	@UiField
	public Button create;

	@UiField
	public Label labelNotification;

	@UiField
	public Heading formTitle;

	@Inject
	FormLayout(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addBodyContent(Widget child) {
		body.add(child);
	}

	public void showNotification(boolean visible) {
		notification.setVisible(visible);
	}

	public void setCreateButtonText(String text) {
		create.setText(text);
	}

	public void setNotificationText(String text) {
		labelNotification.setText(text);
	}
	
	public void setFormTitle(String text){
		formTitle.setText(text);
	}
}