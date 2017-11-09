package com.telosys.gwtp.base.client.util.common.list.view;

import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.util.common.list.presenter.ListPresenter;

public abstract class AbstractListView<P extends ListPresenter<F>, F> extends ViewImpl implements ListView<P, F> {

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

	protected void addDeletionColumn() {
		final Column<F, String> deletion = getDeletionColumn();
		table.addColumn(deletion, "Delete");
		table.setColumnWidth(deletion, 30, Unit.PX);
	}

	protected void addUpdateColumn() {
		final Column<F, String> update = getUpdateColumn();
		table.addColumn(update, "Update");
		table.setColumnWidth(update, 30, Unit.PX);
	}

	private Column<F, String> getDeletionColumn() {
		final Column<F, String> deletion = new Column<F, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(F object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		return deletion;
	}

	private Column<F, String> getUpdateColumn() {
		final Column<F, String> update = new Column<F, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(F object) {
				return "";
			}
		};
		update.setFieldUpdater((index, author, value) -> presenter.onUpdateClick(author));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		return update;

	}

	protected String formatDate(Date date) {
		return date != null ? DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(date) : "";
	}

}