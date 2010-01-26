package net.boklab.document.client.info.edit;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface DocInfoEditorDisplay extends Display {
    HasClickHandlers getCancel();

    HasText getDocumentDescription();

    HasText getDocumentTitle();

    HasClickHandlers getSave();
}
