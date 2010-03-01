package net.boklab.workspace.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserMessageHandler extends EventHandler {

    void onUserMessage(UserMessageEvent event);

}
