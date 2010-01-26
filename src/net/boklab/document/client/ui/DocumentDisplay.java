package net.boklab.document.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasWidgets;

public interface DocumentDisplay extends Display {
    HasWidgets getContents();

    void setInfoDisplay(Display display);
}
