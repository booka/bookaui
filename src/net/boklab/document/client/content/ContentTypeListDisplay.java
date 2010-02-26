package net.boklab.document.client.content;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ContentTypeListDisplay extends Display {

    HasClickHandlers addContentType(String name);

    void setVisible(boolean visible);

}
