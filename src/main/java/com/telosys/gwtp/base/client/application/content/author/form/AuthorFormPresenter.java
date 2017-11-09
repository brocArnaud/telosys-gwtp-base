package com.telosys.gwtp.base.client.application.content.author.form;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.telosys.gwtp.base.client.application.ApplicationPresenter;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormProxy;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormView;
import com.telosys.gwtp.base.client.event.LoadingEvent;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.place.TokenParameters;
import com.telosys.gwtp.base.client.util.common.form.presenter.AbstractFormPresenter;
import com.telosys.gwtp.base.client.util.common.form.view.FormView;
import com.telosys.gwtp.base.shared.api.resources.AuthorResource;
import com.telosys.gwtp.base.shared.dto.AuthorDto;

public class AuthorFormPresenter extends AbstractFormPresenter<AuthorFormProxy, AuthorFormView, AuthorDto, AuthorResource> {

	public interface AuthorFormView extends FormView<AuthorFormPresenter, AuthorDto> {
	}

	@ProxyStandard
	@NameToken(NameTokens.AUTHOR_FORM)
	public interface AuthorFormProxy extends ProxyPlace<AuthorFormPresenter> {
	}

	@Inject
	AuthorFormPresenter(EventBus eventBus, AuthorFormView view, AuthorFormProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN, placeManager);
		getView().setPresenter(this);
	}

	@Override
	public String getListRouteToken() {
		return NameTokens.AUTHOR_LIST;
	}

	@Override
	public void saveAction(AuthorDto data) {
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
			service.withCallback(new CallBack<AuthorDto>() {
				@Override
				public void onSuccess(AuthorDto data) {
					getView().load(data);
					getView().setUpdateMode(updateMode);
					LoadingEvent.fire(AuthorFormPresenter.this, false);
				}
			}).get(Integer.valueOf(id));
		} else {
			getView().load(new AuthorDto());
		}
	}
}