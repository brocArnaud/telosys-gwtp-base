package com.telosys.gwtp.base.client.application.content.workgroup.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.WorkgroupDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class WorkgroupFormViewImpl extends AbstractFormView<WorkgroupFormPresenter, WorkgroupDto> implements WorkgroupFormView {
	interface Binder extends UiBinder<Widget, WorkgroupFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<WorkgroupDto, WorkgroupFormViewImpl> {
	}

	@UiField
	protected IntegerBox id;
	@UiField
	protected TextBox name;
	@UiField
	protected TextBox description;
	@UiField
	protected DatePicker creationDate;

	@Inject
	WorkgroupFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		initDatePicker(creationDate);
		this.driver.initialize(this);
	}
}