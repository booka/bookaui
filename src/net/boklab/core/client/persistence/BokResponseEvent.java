package net.boklab.core.client.persistence;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponse;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class BokResponseEvent<T extends EventHandler> extends GwtEvent<T> {
    private final BokResponse response;
    private final Bok bok;
    private ArrayList<Bok> children;

    public BokResponseEvent(final Bok bok, final BokResponse response) {
	this.bok = bok;
	this.response = response;
    }

    public Bok getBok() {
	return bok;
    }

    public ArrayList<Bok> getChildren() {
	if (children == null) {
	    children = new ArrayList<Bok>();
	    final int total = response.getChildrenSize();
	    for (int index = 0; index < total; index++) {
		children.add(response.getChildren(index));
	    }
	}
	return children;
    }

    public BokResponse getResponse() {
	return response;
    }

    @Override
    public String toDebugString() {
	final String postfix = bok != null ? " " + getBok().getBokType() + "-" + getBok().getId() : "";
	return super.toDebugString() + postfix;
    }
}
