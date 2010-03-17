package net.boklab.module.explore.client.pointer;

import net.boklab.core.client.ui.browser.BrowserItemDisplay;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;

public interface PointerDisplay extends BrowserItemDisplay {

    void addStyleName(String string);

    Label getPointerTitle();

    HasText getPosition();

    void removeStyleName(String string);

}
