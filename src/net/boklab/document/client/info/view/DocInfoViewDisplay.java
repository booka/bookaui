package net.boklab.document.client.info.view;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;

public interface DocInfoViewDisplay extends Display {
    HasText getDocumentDescription();

    HasText getDocumentTitle();

}
