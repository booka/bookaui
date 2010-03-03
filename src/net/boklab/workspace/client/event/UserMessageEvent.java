package net.boklab.workspace.client.event;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.event.shared.GwtEvent;

public class UserMessageEvent extends GwtEvent<UserMessageHandler> {

    public static enum Level {
	info, error, working
    }

    private static final Type<UserMessageHandler> TYPE = new Type<UserMessageHandler>();

    private static int counter = 0;

    public static void fire(final String msg, final Level level, final EventBus eventBus) {
	eventBus.fireEvent(new UserMessageEvent(msg, level));
    }

    public static Type<UserMessageHandler> getType() {
	return TYPE;
    }

    private final String message;

    private final Level level;

    private final int id;

    public UserMessageEvent(final String message) {
	this(message, Level.info);
    }

    public UserMessageEvent(final String message, final Level level) {
	this.message = message;
	this.level = level;
	id = ++counter;
    }

    @Override
    public Type<UserMessageHandler> getAssociatedType() {
	return getType();
    }

    public int getId() {
	return id;
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
