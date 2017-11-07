package com.telosys.gwtp.base.client.application.content.synopsis.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.SynopsisDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class SynopsisFormViewImpl extends AbstractFormView<SynopsisFormPresenter, SynopsisDto> implements SynopsisFormView {
	interface Binder extends UiBinder<Widget, SynopsisFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<SynopsisDto, SynopsisFormViewImpl> {
	}

	@UiField
	protected IntegerBox bookId;
	@UiField
	protected TextBox synopsis;

	@Inject
	SynopsisFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}