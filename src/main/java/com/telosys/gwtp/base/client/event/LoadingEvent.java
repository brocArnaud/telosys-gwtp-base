package com.telosys.gwtp.base.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class LoadingEvent extends GwtEvent<LoadingEvent.LoadingHandler> {
	public interface LoadingHandler extends EventHandler {
		void onLoadingEvent(LoadingEvent event);
	}

	public static final Type<LoadingHandler> TYPE = new Type<>();

	public final boolean show;

	public LoadingEvent(boolean show) {
		this.show = show;
	}

	public static void fire(HasHandlers source, boolean show) {
		source.fireEvent(new LoadingEvent(show));
	}

	@Override
	public Type<LoadingHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadingHandler handler) {
		handler.onLoadingEvent(this);
	}

	public boolean isShow() {
		return show;
	}
}
