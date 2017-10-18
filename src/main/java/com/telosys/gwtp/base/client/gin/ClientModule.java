package com.telosys.gwtp.base.client.gin;

import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule.Builder;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.telosys.gwtp.base.client.application.ApplicationModule;
import com.telosys.gwtp.base.client.place.NameTokens;
import com.telosys.gwtp.base.client.resources.ResourceLoader;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Default module
		final Builder moduleBuilder = new Builder();
		moduleBuilder.defaultPlace(NameTokens.HOME);
		moduleBuilder.errorPlace(NameTokens.HOME);
		moduleBuilder.unauthorizedPlace(NameTokens.HOME);
		moduleBuilder.tokenFormatter(RouteTokenFormatter.class);
		install(moduleBuilder.build());

		// Rest dispatch
		final RestDispatchAsyncModule.Builder dispatchBuilder = new RestDispatchAsyncModule.Builder();
		dispatchBuilder.addGlobalHeaderParam("Access-Control-Allow-Origin").withValue("*");
		dispatchBuilder.addGlobalHeaderParam("Access-Control-Allow-Methods").withValue("GET,POST,PUT,DELETE,OPTIONS");
		dispatchBuilder.addGlobalHeaderParam("Access-Control-Allow-Headers").withValue("Content-Type,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Authorization, X-Requested-With,x-gwt-module-base");
		install(dispatchBuilder.build());
		bindConstant().annotatedWith(RestApplicationPath.class).to("http://localhost:8080/league-factory-api-0.1/api/v1");

		// Application main module
		install(new ApplicationModule());

		// Resource binding
		bind(ResourceLoader.class).asEagerSingleton();
	}
}
