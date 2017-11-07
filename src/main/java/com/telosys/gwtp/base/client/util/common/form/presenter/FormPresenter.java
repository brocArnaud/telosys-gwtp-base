package com.telosys.gwtp.base.client.util.common.form.presenter;

public interface FormPresenter<F> {

	public void save(F data);

	public void reset();

	public void load();

}
