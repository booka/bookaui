package net.boklab.module.explore.client.pointer;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;

public interface PointerDisplay extends Display {

    void addStyleName(String string);

    Label getPointerTitle();

    HasText getPosition();

    HasClickHandlers getSelf();

    void removeStyleName(String string);

}
