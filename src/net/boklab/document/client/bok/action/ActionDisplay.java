package net.boklab.document.client.bok.action;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ActionDisplay extends Display, HasClickHandlers {
    public void setVisible(boolean visible);

    BokAction getAction();

}
