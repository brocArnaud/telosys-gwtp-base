package com.telosys.gwtp.base.client.application.content.employee.group.form;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.EmployeeGroupDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class EmployeeGroupFormViewImpl extends AbstractFormView<EmployeeGroupFormPresenter, EmployeeGroupDto> implements EmployeeGroupFormView {
	interface Binder extends UiBinder<Widget, EmployeeGroupFormViewImpl> {
	}

	interface PlayerDriver extends BeanValidationEditorDriver<EmployeeGroupDto, EmployeeGroupFormViewImpl> {
	}

	@UiField
	protected TextBox employeeCode;

	@UiField
	protected IntegerBox groupId;

	@Inject
	EmployeeGroupFormViewImpl(Binder uiBinder, final PlayerDriver driver) {
		super(driver);
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}

}