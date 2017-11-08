package com.telosys.gwtp.base.client.util.common.form.view;

import com.telosys.gwtp.base.client.util.common.SimpleView;

public interface FormView<P, F> extends SimpleView<P> {
	void showNotification(boolean visible);

	void showNotificationError(boolean visible, String text);

	void load(F data);

	void setUpdateMode(boolean updateMode);

}
