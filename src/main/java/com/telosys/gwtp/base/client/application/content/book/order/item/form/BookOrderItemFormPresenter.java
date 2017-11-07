package com.telosys.gwtp.base.client.application.content.book.order.item.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormProxy;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.BookOrderItemResource;
import com.telosys.gwtp.base.shared.dto.BookOrderItemDto;

public class BookOrderItemFormPresenter extends AbstractFormPresenter<BookOrderItemFormProxy, BookOrderItemFormView, BookOrderItemDto, BookOrderItemResource> {

	public interface BookOrderItemFormView extends FormView<BookOrderItemFormPresenter, BookOrderItemDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.BOOK_ORDER_ITEM_FORM)
	public interface BookOrderItemFormProxy extends ProxyPlace<BookOrderItemFormPresenter> {
	}

	@Inject
	BookOrderItemFormPresenter(EventBus eventBus, BookOrderItemFormView view, BookOrderItemFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public BookOrderItemDto newInstance() {
		return new BookOrderItemDto();
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.BOOK_ORDER_ITEM_LIST;
	}

	@Override
	public void saveAction(BookOrderItemDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getBookOrderId(), data.getBookId());
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
		final String bookOrderId = getCurrentPlaceRequestId(TokenParameters.BOOK_ORDER_ID);
		final String bookId = getCurrentPlaceRequestId(TokenParameters.BOOK_ID);
		updateMode = !bookOrderId.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<BookOrderItemDto>() {
				@Override
				public void onSuccess(BookOrderItemDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(BookOrderItemFormPresenter.this, false);
				}
			}).get(Integer.valueOf(bookOrderId), Integer.valueOf(bookId));
		} else {
			getView().load(newInstance());
		}
	}
}