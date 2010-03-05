package net.boklab.workspace.client.ui.signals;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;

public interface SignalsDisplay extends Display {

    void addMessage(int id, String message, String level);

    HasText getPlace();

    HasText getProject();

    HasText getUser();

    boolean removeMessage(int id);

    void setUserActive(boolean active);
}
