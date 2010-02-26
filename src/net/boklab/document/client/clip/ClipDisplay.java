package net.boklab.document.client.clip;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHTML;

public interface ClipDisplay extends Display {

    void addStyleName(String string);

    boolean areControls();

    HasClickHandlers getActive();

    HasHTML getBody();

    void removeStyleName(String string);

    void setControls(Display display);

    void setViewVisible(boolean visible);
}
