package net.boklab.document.client.content.link;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface LinkEditorDisplay extends Display {

    HasText getBody();

    HasText getBokTitle();

    HasClickHandlers getPreviewAction();

    void setPreviewHtml(String rendered);

}
