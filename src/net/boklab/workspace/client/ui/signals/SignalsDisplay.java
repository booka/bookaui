package net.boklab.workspace.client.ui.signals;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface SignalsDisplay extends Display {

    String USER = "User";

    void addMessage(int id, String message, String level);

    HasText getPlace();

    HasText getProject();

    HasText getUser();

    HasClickHandlers getUserAction();

    boolean removeMessage(int id);

    void setUserActive(boolean active);
}
