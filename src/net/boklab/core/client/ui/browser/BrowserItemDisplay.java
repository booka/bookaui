package net.boklab.core.client.ui.browser;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface BrowserItemDisplay extends Display {
    HasClickHandlers getSelectArea();
}
