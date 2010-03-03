package net.boklab.workspace.client.ui.navigation;

import net.boklab.tools.client.mvp.Display;
import net.boklab.workspace.client.ui.signals.SignalsDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface NavigationDisplay extends Display {
    static final String CONTACT = "contact";
    static final String ACCOUNT = "account";
    static final String CALENDAR = "calendar";
    static final String BOOKA = "booka";
    static final String EDITION = "edition";
    static final String ARCHIVES = "archives";
    static final String LOGIN = "login";
    static final String ENTRANCE = "entrance";

    static final String TITLE = "title";

    HasClickHandlers getLink(String name);

    SignalsDisplay getSignals();

    void setVisible(String item, boolean visible);
}
