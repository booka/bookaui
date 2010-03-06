package net.boklab.workspace.client.msg;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.event.shared.GwtEvent;

public class CreateMessageEvent extends GwtEvent<CreateMessageHandler> {

    public static enum Level {
	info, error, working
    }

    private static final Type<CreateMessageHandler> TYPE = new Type<CreateMessageHandler>();

    private static int counter = 0;

    public static void fire(final String msg, final Level level, final EventBus eventBus) {
	eventBus.fireEvent(new CreateMessageEvent(msg, level));
    }

    public static Type<CreateMessageHandler> getType() {
	return TYPE;
    }

    private final String message;

    private final Level level;

    private final int id;

    public CreateMessageEvent(final String message, final Level level) {
	this.message = message;
	this.level = level;
	id = ++counter;
    }

    @Override
    public Type<CreateMessageHandler> getAssociatedType() {
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
	return super.toDebugString() + "(" + id + ") " + message + "(" + level + ")";
    }

    @Override
    protected void dispatch(final CreateMessageHandler handler) {
	handler.onAddMessage(this);
    }

}
