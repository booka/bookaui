package net.boklab.browser.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

public interface DocumentBrowserDisplay extends Display {

    HasClickHandlers getCreate();

    HasWidgets getList();

    void setCreateVisible(boolean visible);

}
