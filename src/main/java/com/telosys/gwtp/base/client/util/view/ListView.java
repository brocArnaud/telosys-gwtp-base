package com.telosys.gwtp.base.client.util.view;

import java.util.List;

public interface ListView<D, P> extends SimpleView<P> {

	void display(List<D> data);
}
