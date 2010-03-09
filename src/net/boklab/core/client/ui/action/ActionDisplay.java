package net.boklab.core.client.ui.action;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ActionDisplay extends Display, HasClickHandlers {

    void setVisible(boolean visible);
}
