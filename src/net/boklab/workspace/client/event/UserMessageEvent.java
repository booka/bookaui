package net.boklab.workspace.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserMessageEvent extends GwtEvent<UserMessageHandler> {

    public static enum Level {
	info, error, working
    }

    private static final Type<UserMessageHandler> TYPE = new Type<UserMessageHandler>();

    public static Type<UserMessageHandler> getType() {
	return TYPE;
    }

    private final String message;
    private final Level level;

    public UserMessageEvent(final String message) {
	this(message, Level.info);
    }

    public UserMessageEvent(final String message, final Level level) {
	this.message = message;
	this.level = level;
    }

    @Override
    public Type<UserMessageHandler> getAssociatedType() {
	return getType();
    }

    public Level getLevel() {
	return level;
    }

    public String getMessage() {
	return message;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + message + "(" + level + ")";
    }

    @Override
    protected void dispatch(final UserMessageHandler handler) {
	handler.onUserMessage(this);
    }

}
