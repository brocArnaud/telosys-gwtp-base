package com.telosys.gwtp.base.client.application.content.book.order.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormProxy;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.BookOrderResource;
import com.telosys.gwtp.base.shared.dto.BookOrderDto;

public class BookOrderFormPresenter extends AbstractFormPresenter<BookOrderFormProxy, BookOrderFormView, BookOrderDto, BookOrderResource> {

	public interface BookOrderFormView extends FormView<BookOrderFormPresenter, BookOrderDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_ORDER_FORM)
	public interface BookOrderFormProxy extends ProxyPlace<BookOrderFormPresenter> {
	}

	@Inject
	BookOrderFormPresenter(EventBus eventBus, BookOrderFormView view, BookOrderFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public BookOrderDto newInstance() {
		return new BookOrderDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.BOOK_ORDER_LIST;
	}

	@Override
	public void saveAction(BookOrderDto data) {
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
			service.withCallback(new CallBack<BookOrderDto>() {
				@Override
				public void onSuccess(BookOrderDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(BookOrderFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(newInstance());
		}
	}
}