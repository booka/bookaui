package net.boklab.module.entrance.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class EntranceEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final EntranceMessages messages = GWT.create(EntranceMessages.class);
	I18nEntrance.setMessages(messages);
    }

}
