package com.telosys.gwtp.base.client.resources.i18n;

import com.google.gwt.user.cellview.client.Header;

/**
 * Interface used for manage i18n key <br/>
 * WARNING : Note that it is not necessary to explicitly add the keys to the
 * Java interface for ui-binding key. They will be taken directly from the
 * property files for UiBinder. You will need to put the messages into the
 * interface, if you want to access the translations from anywhere else though.
 * 
 * @author Arnaud Brochain (arnaud.brochain@sogeti.com)
 *
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

	@Key("commons.id")
	String id();

	@Key("commons.firstName")
	String firstName();

	@Key("commons.lastName")
	String lastName();

	@Key("commons.delete")
	String delete();

	@Key("commons.update")
	String update();

}
