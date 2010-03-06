package net.boklab.workspace.client.msg;

import com.google.gwt.event.shared.EventHandler;

public interface CreateMessageHandler extends EventHandler {

    void onAddMessage(CreateMessageEvent event);

}
