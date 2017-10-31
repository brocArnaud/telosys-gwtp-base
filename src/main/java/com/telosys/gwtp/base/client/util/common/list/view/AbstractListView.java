package com.telosys.gwtp.base.client.util.common.list.view;

import java.util.List;

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.util.common.list.presenter.ListPresenter;
import com.telosys.gwtp.base.shared.dto.common.IdDto;

public abstract class AbstractListView<P extends ListPresenter<I>, F extends IdDto, I> extends ViewImpl implements ListView<P, F> {

	protected P presenter;

	@UiField
	public CellTable<F> table;

	protected ListDataProvider<F> provider = new ListDataProvider<>();

	public AbstractListView() {
		super();
	}

	/** Init column of the table in this method. */
	public abstract void initCellTable();

	@Override
	public void display(List<F> data) {
		provider.getList().clear();
		provider.getList().addAll(data);
		provider.flush();
	}

	@UiHandler(value = "create")
	public void onCreateClick(ClickEvent event) {
		presenter.onCreateClick();
	}

	@Override
	public void setPresenter(P presenter) {
		this.presenter = presenter;
	}

}