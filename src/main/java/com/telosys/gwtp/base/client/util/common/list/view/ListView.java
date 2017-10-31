package com.telosys.gwtp.base.client.util.common.list.view;

import java.util.List;

import com.telosys.gwtp.base.client.util.common.SimpleView;

public interface ListView<D, P> extends SimpleView<P> {

	void display(List<D> data);
}
