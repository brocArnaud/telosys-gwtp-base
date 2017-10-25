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

/**
 * Main application module, contains :<br/>
 * - Default place settings<br/>
 * - Rest dispatcher configuration<br/>
 * - Resource binding<br/>
 * - Top module installation : @see {@link ApplicationModule}
 * 
 * @author Arnaud Brochain (arnaud.brochain@sogeti.com)
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Default module builder declaration
		final Builder moduleBuilder = new Builder();
		// Set the default place to NameTokens.HOME
		moduleBuilder.defaultPlace(NameTokens.HOME);
		// Set the error place to NameTokens.ERROR
		moduleBuilder.errorPlace(NameTokens.ERROR);
		// Set the unauthorized place to NameTokens.ERROR
		moduleBuilder.unauthorizedPlace(NameTokens.ERROR);
		// Set the route formatter to RouteTokenFormatter ==> RestFull style
		moduleBuilder.tokenFormatter(RouteTokenFormatter.class);
		// ==> Install the defaultModule
		install(moduleBuilder.build());

		// Rest dispatch module builder declaration
		final RestDispatchAsyncModule.Builder dispatchBuilder = new RestDispatchAsyncModule.Builder();
		// Set global header parameter : ACCESS_CONTROL_ALLOW_ORIGIN
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN).withValue(Paths.ALLOWED_ORIGIN);
		// Set global header parameter : ACCESS_CONTROL_ALLOW_METHODS
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS).withValue(Paths.ALLOWED_METHOD);
		// Set global header parameter : ACCESS_CONTROL_ALLOW_HEADERS
		dispatchBuilder.addGlobalHeaderParam(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS).withValue(Paths.ALLOWED_HEADERS);
		// ==> Install the restDispatcherModule
		install(dispatchBuilder.build());

		// Init rest api path
		bindConstant().annotatedWith(RestApplicationPath.class).to(Paths.BASE_URL + Paths.API_ROOT);

		// ==> Install Application main module
		install(new ApplicationModule());

		// Resource binding
		bind(ResourceLoader.class).asEagerSingleton();
	}
}