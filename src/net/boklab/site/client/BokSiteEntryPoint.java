package net.boklab.site.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokSiteEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final SiteMessages messages = GWT.create(SiteMessages.class);
	I18nSite.setMessages(messages);
    }

}
