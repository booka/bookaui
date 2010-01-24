package net.boklab.testing.display;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasClickHandlersStub implements HasClickHandlers {

    private ClickHandler handler;
    private boolean isRemoved;
    private HandlerRegistration registration;

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
	this.isRemoved = false;
	this.handler = handler;
	this.registration = new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		isRemoved = true;
	    }
	};
	return registration;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	handler.onClick((ClickEvent) event);
    }

    public boolean isRemoved() {
	return isRemoved;
    }

}
