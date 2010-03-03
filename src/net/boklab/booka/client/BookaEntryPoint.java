package net.boklab.booka.client;

import net.boklab.tools.client.rest.RestServiceAsync;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;

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
	GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
	    @Override
	    public void onUncaughtException(final Throwable e) {
		GWT.log("Uncaught: ", e);
	    }
	});

	final BookaGinjector injector = GWT.<BookaGinjector> create(BookaGinjector.class);
	final RestServiceAsync restService = injector.getRestServiceAsync();
	final String hostPath = getMeta(CONFIG_HOST);
	restService.setHostPath(hostPath);

	injector.getProjects();

	final BookaAppPresenter booka = injector.getBookaAppPresenter();
	RootLayoutPanel.get().add(booka.getDisplay().asWidget());

	injector.getUserSessionPersistence().requestUserSession();
	injector.getPlaceManager().fireCurrentPlace();
    }

}
