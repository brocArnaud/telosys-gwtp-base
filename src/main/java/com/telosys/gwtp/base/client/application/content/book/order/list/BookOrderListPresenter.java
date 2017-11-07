package com.telosys.gwtp.base.client.application.content.book.order.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListProxy;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.BookOrderResource;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;

public class BookOrderListPresenter extends AbstractListPresenter<BookOrderListProxy, BookOrderListView, BookOrderDto, BookOrderResource> {

	public interface BookOrderListView extends ListView<BookOrderListPresenter, BookOrderDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_ORDER_LIST)
	public interface BookOrderListProxy extends ProxyPlace<BookOrderListPresenter> {
	}

	@Inject
	BookOrderListPresenter(EventBus eventBus, BookOrderListView view, BookOrderListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.BOOK_ORDER_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<BookOrderDto>>() {
			@Override
			public void onSuccess(List<BookOrderDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(BookOrderListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(BookOrderDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getId());
	}

	@Override
	public void onUpdateClick(BookOrderDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, data.getId());
	}

}