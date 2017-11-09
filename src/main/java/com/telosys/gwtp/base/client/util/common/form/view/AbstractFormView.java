package com.telosys.gwtp.base.client.util.common.form.view;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.ValueListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.view.client.ProvidesKey;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.util.common.form.presenter.FormPresenter;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public abstract class AbstractFormView<P extends FormPresenter<F>, F> extends ViewImpl implements FormView<P, F>, Editor<F> {

	protected P presenter;

	protected final BeanValidationEditorDriver<F, AbstractFormView<P, F>> driver;

	@Ignore
	@UiField
	public PanelBody body;

	@Ignore
	@UiField
	public Button create;

	@Ignore
	@UiField
	public Column notification;
	@Ignore
	@UiField
	public Label labelNotification;

	@Ignore
	@UiField
	public Column notificationError;
	@Ignore
	@UiField
	public Label labelNotificationError;

	@SuppressWarnings("unchecked")
	public AbstractFormView(final BeanValidationEditorDriver<F, ? extends AbstractFormView<P, F>> pdriver) {
		super();
		this.driver = (BeanValidationEditorDriver<F, AbstractFormView<P, F>>) pdriver;
	}

	/** Override this method to perform action on data before validation. */
	protected F beforeValidation(F data) {
		return data;
	}

	@UiHandler("create")
	public void onCreateClick(ClickEvent event) {
		F data = driver.flush();
		data = beforeValidation(data);
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<F>> violations = validator.validate(data, Default.class);
		for (ConstraintViolation<F> constraintViolation : violations) {
			GWT.log("constraintViolation :" + constraintViolation.getMessage() + " | " + constraintViolation.getPropertyPath());
		}
		if (!violations.isEmpty()) {
			driver.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(violations));
		} else {
			presenter.save(data);
		}
	}

	@UiHandler("reset")
	public void onResetClick(ClickEvent event) {
		presenter.reset();
	}

	@Override
	public void showNotification(boolean visible) {
		notification.setVisible(visible);
	}

	@Override
	public void load(F team) {
		GWT.log("====> load go to edit");
		this.driver.edit(team);
	}

	@Override
	public void setUpdateMode(boolean updateMode) {
		create.setText(updateMode ? "Save" : "Create");
		labelNotification.setText(updateMode ? "Saved succesfully" : "Created succesfully");
	}

	@Override
	public void setPresenter(P presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showNotificationError(boolean visible, String text) {
		labelNotificationError.setText(text);
		notificationError.setVisible(visible);
	}

	@Ignore
	protected ValueListBox<ListItemDto> initListItemBox() {
		return new ValueListBox<>(new AbstractRenderer<ListItemDto>() {
			@Override
			public String render(ListItemDto item) {
				return (item != null && item.getLabel() != null) ? item.getLabel() : "";
			}
		}, new ProvidesKey<ListItemDto>() {

			@Override
			public Object getKey(ListItemDto item) {
				return (item != null && item.getValue() != null) ? item.getValue() : "";
			}
		});
	}
}