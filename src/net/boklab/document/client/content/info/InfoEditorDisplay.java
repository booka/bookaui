package net.boklab.document.client.content.info;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;

public interface InfoEditorDisplay extends Display {

    HasText getBokTitle();

    HasText getDescription();

}
