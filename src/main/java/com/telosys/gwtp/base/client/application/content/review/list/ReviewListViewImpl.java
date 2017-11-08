package com.telosys.gwtp.base.client.application.content.review.list;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListView;
import com.telosys.gwtp.base.client.util.common.list.view.AbstractListView;
import com.telosys.gwtp.base.shared.dto.ReviewDto;

public class ReviewListViewImpl extends AbstractListView<ReviewListPresenter, ReviewDto> implements ReviewListView {
	interface Binder extends UiBinder<Widget, ReviewListViewImpl> {
	}

	@Inject
	ReviewListViewImpl(Binder uiBinder) {
		super();
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	@Override
	public void initCellTable() {
		final TextColumn<ReviewDto> col1 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getCustomerCode());
			}
		};
		table.addColumn(col1, "Customer Code");
		final TextColumn<ReviewDto> col2 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getCreation());
			}
		};
		table.addColumn(col2, "Creation Date");
		final TextColumn<ReviewDto> col3 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getLastUpdate());
			}
		};
		table.addColumn(col3, "Last update");
		final TextColumn<ReviewDto> col4 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getBookId());
			}
		};
		table.addColumn(col4, "Book Id");
		final TextColumn<ReviewDto> col5 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getReviewNote());
			}
		};
		table.addColumn(col5, "Note");
		final TextColumn<ReviewDto> col6 = new TextColumn<ReviewDto>() {

			@Override
			public String getValue(final ReviewDto object) {
				return String.valueOf(object.getReviewText());
			}
		};
		table.addColumn(col6, "Description");
		// Actions column
		addDeletionColumn();
		addUpdateColumn();
		provider.addDataDisplay(table);
	}
}