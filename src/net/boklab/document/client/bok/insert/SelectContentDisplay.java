package net.boklab.document.client.bok.insert;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface SelectContentDisplay extends Display {

    HasClickHandlers getAccept();

    HasClickHandlers getCancel();

}
