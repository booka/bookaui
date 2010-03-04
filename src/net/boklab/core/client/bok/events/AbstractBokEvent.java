package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class AbstractBokEvent<T extends EventHandler> extends GwtEvent<T> {

    protected final Bok bok;

    public AbstractBokEvent(final Bok bok) {
	this.bok = bok;
    }

    public Bok getBok() {
	return bok;
    }

    public boolean isBokType(final String bokType) {
	return bok.getBokType().equals(bokType);
    }

    @Override
    public String toDebugString() {
	final String postfix = bok != null ? " " + getBok().getBokType() + "-" + getBok().getId() : "";
	return super.toDebugString() + postfix;
    }
}
