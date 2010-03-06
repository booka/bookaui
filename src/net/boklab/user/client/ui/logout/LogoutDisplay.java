package net.boklab.user.client.ui.logout;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface LogoutDisplay extends Display {

    HasClickHandlers getLogoutAction();

    HasText getUser();

}
