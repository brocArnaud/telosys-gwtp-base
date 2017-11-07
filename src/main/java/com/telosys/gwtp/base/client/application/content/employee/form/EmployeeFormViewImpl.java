package com.telosys.gwtp.base.client.application.content.employee.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class EmployeeFormViewImpl extends AbstractFormView<EmployeeFormPresenter, EmployeeDto> implements EmployeeFormView {
	interface Binder extends UiBinder<Widget, EmployeeFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<EmployeeDto, EmployeeFormViewImpl> {
	}
	
	@UiField
	protected TextBox code;
	@UiField
	protected TextBox shopCode;
	@UiField
	protected TextBox firstName;
	@UiField
	protected TextBox lastName;
	@UiField
	protected IntegerBox manager;
	@UiField
	protected IntegerBox badgeNumber;
	@UiField
	protected TextBox email;

	@Inject
	EmployeeFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}
}