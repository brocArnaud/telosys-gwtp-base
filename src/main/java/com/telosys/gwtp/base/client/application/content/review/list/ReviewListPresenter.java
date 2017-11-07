package com.telosys.gwtp.base.client.application.content.review.list;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListProxy;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.list.presenter.AbstractListPresenter;
import com.telosys.gwtp.base.client.util.common.list.view.ListView;
import com.telosys.gwtp.base.shared.api.resources.ReviewResource;
import com.telosys.gwtp.base.shared.dto.ReviewDto;

public class ReviewListPresenter extends AbstractListPresenter<ReviewListProxy, ReviewListView, ReviewDto, ReviewResource> {

	public interface ReviewListView extends ListView<ReviewListPresenter, ReviewDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.REVIEW_LIST)
	public interface ReviewListProxy extends ProxyPlace<ReviewListPresenter> {
	}

	@Inject
	ReviewListPresenter(EventBus eventBus, ReviewListView view, ReviewListProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}

	@Override
	public String getFormRouteToken() {
		return NameTokens.REVIEW_FORM;
	}

	@Override
	public void onCreateClick() {
		revealPlace(getFormRouteToken(), TokenParameters.CUSTOMER_CODE, TokenParameters.DEFAULT_ID, TokenParameters.BOOK_ID, TokenParameters.DEFAULT_ID);
	}

	@Override
	public void load() {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<List<ReviewDto>>() {
			@Override
			public void onSuccess(List<ReviewDto> datas) {
				getView().display(datas);
				LoadingEvent.fire(ReviewListPresenter.this, false);
			}
		}).findAll();
	}

	@Override
	public void onDeleteClick(ReviewDto data) {
		LoadingEvent.fire(this, true);
		service.withCallback(new CallBack<Void>() {
			@Override
			public void onSuccess(Void nothing) {
				load();
			}
		}).delete(data.getCustomerCode(), data.getBookId());
	}

	@Override
	public void onUpdateClick(ReviewDto data) {
		revealPlace(getFormRouteToken(), TokenParameters.CUSTOMER_CODE, data.getCustomerCode(), TokenParameters.BOOK_ID, String.valueOf(data.getBookId()));
	}

}