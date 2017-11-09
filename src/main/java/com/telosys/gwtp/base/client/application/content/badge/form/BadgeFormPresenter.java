package com.telosys.gwtp.base.client.application.content.badge.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormProxy;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.BadgeResource;
import com.telosys.gwtp.base.shared.dto.BadgeDto;

public class BadgeFormPresenter extends AbstractFormPresenter<BadgeFormProxy, BadgeFormView, BadgeDto, BadgeResource> {

	public interface BadgeFormView extends FormView<BadgeFormPresenter, BadgeDto> {
		String getBadgeNumberValue();
	}

	@ProxyStandard
	@NameToken(NameTokens.BADGE_FORM)
	public interface BadgeFormProxy extends ProxyPlace<BadgeFormPresenter> {
	}

	@Inject
	BadgeFormPresenter(EventBus eventBus, BadgeFormView view, BadgeFormProxy proxy, PlaceManager placeManager, RestDispatch dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager, dispatcher);
		getView().setPresenter(this);
	}


	@Override
	public String getListRouteToken() {
		return NameTokens.AUTHOR_LIST;
	}

	@Override
	public void saveAction(BadgeDto data) {
		LoadingEvent.fire(this, true);
		if (updateMode) {
			service.withCallback(new CallBack<Void>() {
				@Override
				public void onSuccess(Void nothing) {
					success();
				}
			}).update(data, data.getBadgeNumber());
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
		final String id = getCurrentPlaceRequestId(TokenParameters.BADGE_NUMBER);
		updateMode = !id.equals(TokenParameters.DEFAULT_ID);
		if (updateMode) {
			LoadingEvent.fire(this, true);
			service.withCallback(new CallBack<BadgeDto>() {
				@Override
				public void onSuccess(BadgeDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(BadgeFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(new BadgeDto());
		}
	}

	@Override
	public void manageConflictError() {
		logger.severe("A conflict error occured");
		getView().showNotificationError(true, "Badge with Badge number value ( "+getView().getBadgeNumberValue()+" ) already exist !");
	}

	@Override
	public void manageNotFoundError() {
		getView().showNotificationError(true, "Badge with Badge number value ( "+getView().getBadgeNumberValue()+" ) dont exist !");
	}
}