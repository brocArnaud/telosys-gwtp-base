package com.telosys.gwtp.base.client.application.content.book.order.item.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListProxy;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.BookOrderItemResource;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;

public class BookOrderItemListPresenter extends AbstractListPresenter<BookOrderItemListProxy, BookOrderItemListView, BookOrderItemDto, BookOrderItemResource> {

	public interface BookOrderItemListView extends ListView<BookOrderItemListPresenter, BookOrderItemDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_ORDER_ITEM_LIST)
	public interface BookOrderItemListProxy extends ProxyPlace<BookOrderItemListPresenter> {
	}

	@Inject
	BookOrderItemListPresenter(EventBus eventBus, BookOrderItemListView view, BookOrderItemListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.BOOK_ORDER_ITEM_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.BOOK_ORDER_ID, TokenParameters.DEFAULT_ID, TokenParameters.BOOK_ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<BookOrderItemDto>>() {
			@Override
			public void onSuccess(List<BookOrderItemDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(BookOrderItemListPresenter.this, false);
			}
		}).findAll();
	}

	@Override
	public void onDeleteClick(BookOrderItemDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getBookOrderId(), data.getBookId());
	}

	@Override
	public void onUpdateClick(BookOrderItemDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.BOOK_ORDER_ID, data.getBookOrderId(), TokenParameters.BOOK_ID, data.getBookId());
	}

}