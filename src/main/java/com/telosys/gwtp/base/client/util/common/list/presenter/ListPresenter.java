package com.telosys.gwtp.base.client.util.common.list.presenter;

public interface ListPresenter<I> {

	public void onCreateClick();

	public void onDeleteClick(I dataId);

	public void onUpdateClick(I dataId);

}
