package net.boklab.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokProjectEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final ProjectMessages messages = GWT.create(ProjectMessages.class);
	I18nProject.setMessages(messages);
    }

}
