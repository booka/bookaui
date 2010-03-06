package net.boklab.workspace.client.msg;

import com.google.gwt.event.shared.GwtEvent;

public class RemoveMessageEvent extends GwtEvent<RemoveMessageHandler> {

    private static final Type<RemoveMessageHandler> TYPE = new Type<RemoveMessageHandler>();

    public static Type<RemoveMessageHandler> getType() {
	return TYPE;
    }

    private final int id;

    public RemoveMessageEvent(final int messageId) {
	id = messageId;
    }

    @Override
    public Type<RemoveMessageHandler> getAssociatedType() {
	return getType();
    }

    public int getId() {
	return id;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + "(" + id + ")";
    }

    @Override
    protected void dispatch(final RemoveMessageHandler handler) {
	handler.onRemoveMessage(this);
    }

}
