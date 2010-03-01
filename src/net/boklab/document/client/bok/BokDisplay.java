package net.boklab.document.client.bok;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.user.client.ui.HasHTML;

public interface BokDisplay extends Display {

    void addStyleName(String string);

    HasClickHandlers getActive();

    HasHTML getBody();

    HasClickHandlers getInsertAfter();

    HasClickHandlers getInsertBefore();

    HasMouseOutHandlers getMouseOut();

    HasMouseOverHandlers getMouseOver();

    boolean hasControls();

    void removeStyleName(String string);

    void setControls(Display display);

    void setSlotsVisible(boolean visible);

    void setViewVisible(boolean visible);

    void setWaitingVisible(boolean visible);
}
