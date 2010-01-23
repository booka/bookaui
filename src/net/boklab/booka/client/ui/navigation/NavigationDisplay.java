package net.boklab.booka.client.ui.navigation;

import java.util.Set;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface NavigationDisplay extends Display {
    static final String CONTACT = "contact";
    static final String ACCOUNT = "account";
    static final String CALENDAR = "calendar";
    static final String BOOKA = "booka";
    static final String EDITION = "edition";
    static final String ARCHIVES = "archives";
    static final String LOGIN = "login";

    HasClickHandlers getLink(String name);

    Set<String> getLinkNames();

    void setVisible(String item, boolean visible);

}
