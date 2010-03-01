package net.boklab.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokUserEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final UserMessages messages = GWT.create(UserMessages.class);
	I18nUser.setMessages(messages);
    }

}
