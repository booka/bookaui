package net.boklab.module.forum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class ForumEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	I18nForum.t = GWT.create(ForumMessages.class);
    }

}
