package com.telosys.gwtp.base.client.util.view;

import com.gwtplatform.mvp.client.View;

public interface SimpleView<P> extends View {
	void setPresenter(P presenter);
}
