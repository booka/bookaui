package net.boklab.core.client.persistence;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponse;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class BokResponseEvent<T extends EventHandler> extends GwtEvent<T> {
    private final BokResponse response;
    private Bok bok;
    private ArrayList<Bok> children;

    public BokResponseEvent(final BokResponse response) {
	this.response = response;
    }

    public Bok getBok() {
	if (bok == null) {
	    bok = response.getBok();
	}
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
	return super.toDebugString() + bok.getBokType() + bok.getId();
    }
}
