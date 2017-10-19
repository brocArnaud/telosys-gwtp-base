package com.telosys.gwtp.base.client.gin;

import com.google.gwt.thirdparty.guava.common.net.HttpHeaders;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule.Builder;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.telosys.gwtp.base.client.application.ApplicationModule;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.resources.ResourceLoader;
import com.telosys.gwtp.base.shared.api.Paths;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Default module
		final Builder moduleBuilder = new Builder();
		moduleBuilder.defaultPlace(NameTokens.HOME);
		moduleBuilder.errorPlace(NameTokens.ERROR);
		moduleBuilder.unauthorizedPlace(NameTokens.ERROR);
		moduleBuilder.tokenFormatter(RouteTokenFormatter.class);
		install(moduleBuilder.build());

		// Rest dispatch
		final RestDispatchAsyncModule.Builder dispatchBuilder = new RestDispatchAsyncModule.Builder();
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN).withValue(Paths.ALLOWED_ORIGIN);
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS).withValue(Paths.ALLOWED_METHOD);
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS).withValue(Paths.ALLOWED_HEADERS);
		install(dispatchBuilder.build());
		bindConstant().annotatedWith(RestApplicationPath.class).to(Paths.BASE_URL + Paths.API_ROOT);

		// Application main module
		install(new ApplicationModule());

		// Resource binding
		bind(ResourceLoader.class).asEagerSingleton();
	}
}
