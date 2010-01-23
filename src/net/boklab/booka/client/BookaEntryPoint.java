package net.boklab.booka.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BookaEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	BookaGinjector injector = GWT.<BookaGinjector> create(BookaGinjector.class);
	injector.getApp();
    }

}
