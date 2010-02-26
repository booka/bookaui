package net.boklab.document.client.slot;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface SlotDisplay extends Display {

    HasClickHandlers getOpen();

    boolean hasContentTypeList();

    void setList(Display display);

}
