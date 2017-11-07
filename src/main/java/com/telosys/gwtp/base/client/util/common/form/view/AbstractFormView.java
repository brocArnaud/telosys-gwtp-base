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

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.util.common.form.presenter.FormPresenter;

import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

public abstract class AbstractFormView<P extends FormPresenter<F>, F> extends ViewImpl implements FormView<P, F>, Editor<F> {

	protected P presenter;

	protected final BeanValidationEditorDriver<F, AbstractFormView<P, F>> driver;

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

	@SuppressWarnings("unchecked")
	public AbstractFormView(final BeanValidationEditorDriver<F, ? extends AbstractFormView<P, F>> pdriver) {
		super();
		this.driver = (BeanValidationEditorDriver<F, AbstractFormView<P, F>>) pdriver;
	}

	@UiHandler("create")
	public void onCreateClick(ClickEvent event) {
		F data = driver.flush();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<F>> violations = validator.validate(data, Default.class);
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
}