package com.telosys.gwtp.base.client.util.common.form.presenter;

public interface FormPresenter<F, I> {

	public void save(F player, I id);

	public void reset();

	public void load();

}
