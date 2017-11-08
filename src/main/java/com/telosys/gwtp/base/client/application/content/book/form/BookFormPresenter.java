package com.telosys.gwtp.base.client.application.content.book.form;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormProxy;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.api.resources.BookResource;
import com.telosys.gwtp.base.shared.api.resources.PublisherResource;
import com.telosys.gwtp.base.shared.dto.book.BookDto;
import com.telosys.gwtp.base.shared.dto.common.ListItemDto;

public class BookFormPresenter extends AbstractFormPresenter<BookFormProxy, BookFormView, BookDto, BookResource> {

	public interface BookFormView extends FormView<BookFormPresenter, BookDto> {

		void loadPublisher(List<ListItemDto> items);

		void loadAuthor(List<ListItemDto> items);
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_FORM)
	public interface BookFormProxy extends ProxyPlace<BookFormPresenter> {
	}

	@Inject
	ResourceDelegate<PublisherResource> publisherService;

	@Inject
	ResourceDelegate<AuthorResource> authorService;

	@Inject
	BookFormPresenter(EventBus eventBus, BookFormView view, BookFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		LoadingEvent.fire(this, true);
		publisherService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> items) {
				getView().loadPublisher(items);
				LoadingEvent.fire(BookFormPresenter.this, false);
			}
		}).listItems();
		authorService.withCallback(new CallBack<List<ListItemDto>>() {
			@Override
			public void onSuccess(List<ListItemDto> items) {
				getView().loadAuthor(items);
				LoadingEvent.fire(BookFormPresenter.this, false);
			}
		}).listItems();
	}

	@Override
	public BookDto newInstance() {
		return new BookDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.BOOK_LIST;
	}

	@Override
	public void saveAction(BookDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getId());
		} else {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).create(data);
		}
	}

	@Override
	public void loadAction() {
		final String id = getCurrentPlaceRequestId();
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<BookDto>() {
				@Override
				public void onSuccess(BookDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(BookFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(newInstance());
		}
	}
}