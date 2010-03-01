package net.boklab.user.client.ui.login;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface LoginDisplay extends Display {

    HasText getEmail();

    HasClickHandlers getLogin();

    HasText getPassword();

}
