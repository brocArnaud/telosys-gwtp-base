package com.telosys.gwtp.base.client.application.content.author.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.AuthorDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class AuthorFormViewImpl extends AbstractFormView<AuthorFormPresenter, AuthorDto> implements AuthorFormView {
	interface Binder extends UiBinder<Widget, AuthorFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<AuthorDto, AuthorFormViewImpl> {
	}

	@UiField
	protected TextBox firstName;
	@UiField
	protected TextBox lastName;
	@UiField
	protected IntegerBox id;

	@Inject
	AuthorFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}