package com.telosys.gwtp.base.client.application.content.home;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter.HomeView;

public class HomeViewImpl extends ViewImpl implements HomeView {
    interface Binder extends UiBinder<Widget, HomeViewImpl> {
    }

    @Inject
    HomeViewImpl(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}