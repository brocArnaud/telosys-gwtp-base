package com.telosys.gwtp.base.client.application.content.shop.list;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.ShopDto;

public class ShopListViewImpl extends AbstractListView<ShopListPresenter, ShopDto> implements ShopListView {
	interface Binder extends UiBinder<Widget, ShopListViewImpl> {
	}

	@Inject
	ShopListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<ShopDto> col1 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getCode());
			}
		};
		table.addColumn(col1, "Code");
		final TextColumn<ShopDto> col2 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getName());
			}
		};
		table.addColumn(col2, "Name");
		final TextColumn<ShopDto> col3 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getPhone());
			}
		};
		table.addColumn(col3, "Phone");
		final TextColumn<ShopDto> col4 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getEmail());
			}
		};
		table.addColumn(col4, "Email");
		final TextColumn<ShopDto> col5 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getExecutive());
			}
		};
		table.addColumn(col5, "Executive");
		final TextColumn<ShopDto> col6 = new TextColumn<ShopDto>() {

			@Override
			public String getValue(final ShopDto object) {
				return String.valueOf(object.getAddress1() + StringUtils.SPACE + object.getAddress2()+ StringUtils.SPACE + object.getZipCode()+ StringUtils.SPACE + object.getCity());
			}
		};
		table.addColumn(col6, "Adress");
		final Column<ShopDto, String> deletion = new Column<ShopDto, String>(new ButtonCell(ButtonType.DANGER, IconType.TRASH)) {
			@Override
			public String getValue(ShopDto object) {
				return "";
			}
		};
		deletion.setFieldUpdater((index, player, value) -> presenter.onDeleteClick(player));
		deletion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(deletion, "Delete");

		final Column<ShopDto, String> update = new Column<ShopDto, String>(new ButtonCell(ButtonType.SUCCESS, IconType.PENCIL)) {
			@Override
			public String getValue(ShopDto object) {
				return "";
			}
		};
		update.setFieldUpdater((index, author, value) -> presenter.onUpdateClick(author));
		update.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		table.addColumn(update, "Update");
		table.setColumnWidth(deletion, 30, Unit.PX);
		table.setColumnWidth(update, 30, Unit.PX);
		provider.addDataDisplay(table);
	}
}