package net.boklab.document.client.content.debug;

import net.boklab.document.client.content.ContentTypeEditorDisplay;

import com.google.gwt.user.client.ui.HasText;

public interface DebugEditorDisplay extends ContentTypeEditorDisplay {

    HasText getClipTitle();

    HasText getContentType();

    HasText getInfo();

    HasText getPosition();

}
