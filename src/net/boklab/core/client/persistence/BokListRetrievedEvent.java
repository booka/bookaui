package net.boklab.core.client.persistence;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponse;

public class BokListRetrievedEvent extends BokResponseEvent<BokListRetrievedHandler> {

    private static final Type<BokListRetrievedHandler> TYPE = new Type<BokListRetrievedHandler>();

    public static Type<BokListRetrievedHandler> getType() {
	return TYPE;
    }

    public BokListRetrievedEvent(final BokResponse response) {
	super(response);
    }

    @Override
    public Type<BokListRetrievedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    public Bok getBok() {
	assert false : "bok list doesn't have bok";
	return null;
    }

    @Override
    public String toDebugString() {
	String name = this.getClass().getName();
	name = name.substring(name.lastIndexOf(".") + 1);
	return "event: " + name + ":" + getChildren().size() + " boks.";
    }

    @Override
    protected void dispatch(final BokListRetrievedHandler handler) {
	handler.onBokListRetrieved(this);
    }

}
