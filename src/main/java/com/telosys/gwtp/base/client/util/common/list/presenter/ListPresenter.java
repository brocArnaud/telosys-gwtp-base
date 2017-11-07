package com.telosys.gwtp.base.client.util.common.list.presenter;

public interface ListPresenter<F> {

	public void onCreateClick();

	public void onDeleteClick(F data);

	public void onUpdateClick(F data);

}
