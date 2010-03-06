package net.boklab.workspace.client.msg;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.CreateMessageEvent.Level;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MessageManager {
    public static final int NONE = -1;

    public static boolean isNot(final int opening) {
	return opening == NONE;
    }

    private final EventBus eventBus;

    @Inject
    public MessageManager(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    public void addCreateMessageHandler(final CreateMessageHandler handler) {
	eventBus.addHandler(CreateMessageEvent.getType(), handler);
    }

    public void addRemoveMessageHandler(final RemoveMessageHandler handler) {
	eventBus.addHandler(RemoveMessageEvent.getType(), handler);
    }

    public int createMessage(final String message, final Level level) {
	final CreateMessageEvent event = new CreateMessageEvent(message, level);
	eventBus.fireEvent(event);
	return event.getId();
    }

    public int removeMessage(final int id) {
	if (id != -1) {
	    eventBus.fireEvent(new RemoveMessageEvent(id));
	}
	return -1;
    }
}
