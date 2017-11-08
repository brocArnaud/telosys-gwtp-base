package com.telosys.gwtp.base.client.application.content.book.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListProxy;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.BookResource;
import com.telosys.gwtp.base.shared.dto.book.BookDto;

public class BookListPresenter extends AbstractListPresenter<BookListProxy, BookListView, BookDto, BookResource> {

	public interface BookListView extends ListView<BookListPresenter, BookDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_LIST)
	public interface BookListProxy extends ProxyPlace<BookListPresenter> {
	}

	@Inject
	BookListPresenter(EventBus eventBus, BookListView view, BookListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.BOOK_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<BookDto>>() {
			@Override
			public void onSuccess(List<BookDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(BookListPresenter.this, false);
			}
		}).getAll();
	}

	@Override
	public void onDeleteClick(BookDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getId());
	}

	@Override
	public void onUpdateClick(BookDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.ID, data.getId());
	}

}