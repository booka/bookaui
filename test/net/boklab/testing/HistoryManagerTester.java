package net.boklab.testing;

import java.util.ArrayList;

import net.boklab.tools.client.place.HistoryManager;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class HistoryManagerTester implements HistoryManager {

    private static class VCEvent extends ValueChangeEvent<String> {
	protected VCEvent(String value) {
	    super(value);
	}
    }

    private ValueChangeHandler<String> lastHandler;
    private final ArrayList<ValueChangeHandler<String>> handlers;
    private final ArrayList<String> items;

    private String lastToken;

    public HistoryManagerTester() {
	this.handlers = new ArrayList<ValueChangeHandler<String>>();
	items = new ArrayList<String>();
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
	handlers.add(handler);
	this.lastHandler = handler;
	HandlerRegistration registration = new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		handlers.remove(handler);
	    }
	};
	return registration;
    }

    @Override
    public void fireCurrentHistoryState() {
	if (lastToken != null) {
	    fire(lastToken);
	}
    }

    public ValueChangeHandler<String> getLastHandler() {
	return lastHandler;
    }

    @Override
    public void newItem(String token, boolean issueEvent) {
	items.add(token);
	lastToken = token;
	if (issueEvent) {
	    fire(token);
	}
    }

    private void fire(String token) {
	for (ValueChangeHandler<String> handler : handlers) {
	    handler.onValueChange(new VCEvent(token));
	}
    }

}
