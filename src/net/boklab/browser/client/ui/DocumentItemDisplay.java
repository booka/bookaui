package net.boklab.browser.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;

public interface DocumentItemDisplay extends Display {

    HasClickHandlers getClickeable();

    HasHTML getDescription();

    HasText getDocumentTitle();

    HasHTML getExtra();

}
