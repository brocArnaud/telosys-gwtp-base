package com.telosys.gwtp.base.client.util.common.list.view;

import java.util.List;

import com.telosys.gwtp.base.client.util.common.SimpleView;

public interface ListView<P, F> extends SimpleView<P> {

	void display(List<F> data);
}
