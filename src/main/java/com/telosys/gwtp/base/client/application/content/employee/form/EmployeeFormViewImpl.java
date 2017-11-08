package com.telosys.gwtp.base.client.application.content.employee.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormView;
import com.telosys.gwtp.base.client.util.common.form.view.AbstractFormView;
import com.telosys.gwtp.base.shared.dto.CustomerDto;
import com.telosys.gwtp.base.shared.dto.EmployeeDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public class EmployeeFormViewImpl extends AbstractFormView<EmployeeFormPresenter, EmployeeDto> implements EmployeeFormView {
	interface Binder extends UiBinder<Widget, EmployeeFormViewImpl> {
	}

	interface Driver extends BeanValidationEditorDriver<EmployeeDto, EmployeeFormViewImpl> {
	}

	@UiField
	protected TextBox code;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> shopCode;
	@UiField
	protected TextBox firstName;
	@UiField
	protected TextBox lastName;
	@UiField
	protected IntegerBox manager;
	@Ignore
	@UiField(provided = true)
	protected ValueListBox<ListItemDto> badgeNumber;
	@UiField
	protected TextBox email;

	@Inject
	EmployeeFormViewImpl(Binder uiBinder, final Driver driver) {
		super(driver);
		shopCode = initListItemBox();
		badgeNumber = initListItemBox();
		initWidget(uiBinder.createAndBindUi(this));
		this.driver.initialize(this);
	}

	@Override
	protected EmployeeDto beforeValidation(EmployeeDto data) {
		data.setShopCode(shopCode.getValue().getValue());
		if (badgeNumber.getValue() != null && badgeNumber.getValue().getValue() != null) {
			data.setBadgeNumber(Integer.valueOf(badgeNumber.getValue().getValue()));
		}
		return data;
	}

	@Override
	public void loadShop(List<ListItemDto> items) {
		shopCode.setValue(items.get(0));
		shopCode.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}

	@Override
	public void loadBadge(List<ListItemDto> items) {
		badgeNumber.setValue(items.get(0));
		badgeNumber.setAcceptableValues(items.stream().collect(Collectors.toList()));
	}
}