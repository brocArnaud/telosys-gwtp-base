package com.telosys.gwtp.base.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface AppResources extends ClientBundle {
	interface Normalize extends CssResource {
	}

	interface Style extends CssResource {

		@ClassName("main-wrapper")
		String mainWrapper();

		@ClassName("icon-header")
		String iconHeader();

		@ClassName("icon-logout")
		String iconLogout();

		@ClassName("button-create")
		String buttonCreate();

		@ClassName("form-panel-footer")
		String formPanelFooter();

		@ClassName("notification-container")
		String notificationContainer();

		@ClassName("spinner")
		String spinner();

		@ClassName("bounce1")
		String bounce1();

		@ClassName("bounce2")
		String bounce2();

		@ClassName("bounce3")
		String bounce3();
		
		@ClassName("clickable")
		String clickable();
		
		@ClassName("footer-bar")
		String footerBar();

	}

	@Source("gss/normalize.gss")
	Normalize normalize();

	@Source("gss/style.gss")
	Style style();

	@Source("image/logo.png")
	ImageResource logo();

	@Source("image/error.png")
	ImageResource error();
}
