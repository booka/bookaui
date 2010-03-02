package net.boklab.document.client.content.debug;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;

public interface DebugEditorDisplay extends Display {

    HasText getClipTitle();

    HasText getContentType();

    HasText getInfo();

    HasText getPosition();

}
