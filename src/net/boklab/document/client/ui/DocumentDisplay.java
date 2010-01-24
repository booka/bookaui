package net.boklab.document.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public interface DocumentDisplay extends Display {
    HasWidgets getContent();

    HasText getDocumentDescription();

    HasText getDocumentTitle();

}
