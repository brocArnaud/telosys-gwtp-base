package com.telosys.gwtp.base.client.application.content.review.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormProxy;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.ReviewResource;
import com.telosys.gwtp.base.shared.dto.ReviewDto;

public class ReviewFormPresenter extends AbstractFormPresenter<ReviewFormProxy, ReviewFormView, ReviewDto, ReviewResource> {

	public interface ReviewFormView extends FormView<ReviewFormPresenter, ReviewDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.REVIEW_FORM)
	public interface ReviewFormProxy extends ProxyPlace<ReviewFormPresenter> {
	}

	@Inject
	ReviewFormPresenter(EventBus eventBus, ReviewFormView view, ReviewFormProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
		getView().setPresenter(this);
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.REVIEW_LIST;
	}

	@Override
	public void saveAction(ReviewDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getCustomerCode(), data.getBookId());
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
		final String customerCode = getCurrentPlaceRequestId(TokenParameters.CUSTOMER_CODE);
		final String bookId = getCurrentPlaceRequestId(TokenParameters.BOOK_ID);
		updateMode = !customerCode.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<ReviewDto>() {
				@Override
				public void onSuccess(ReviewDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(ReviewFormPresenter.this, false);
				}
			}).get(customerCode, Integer.valueOf(bookId));
		} else {
			getView().load(new ReviewDto());
		}
	}
}