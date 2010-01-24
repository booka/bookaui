package net.boklab.booka.client;

import net.boklab.tools.client.rest.RestServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class BookaEntryPoint implements EntryPoint {
    private static final String CONFIG_HOST = "booka.serverPath";

    public static final String getMeta(final String id) {
	String value = null;
	final Element element = DOM.getElementById(id);
	if (element != null) {
	    value = element.getPropertyString("content");
	}
	return value;
    }

    @Override
    public void onModuleLoad() {
	BookaGinjector injector = GWT.<BookaGinjector> create(BookaGinjector.class);
	RestServiceAsync restService = injector.getRestServiceAsync();
	String hostPath = getMeta(CONFIG_HOST);
	restService.setHostPath(hostPath);
	injector.getApp();
    }

}
