package net.boklab.document.client.content.info;

import net.boklab.document.client.content.ContentTypeEditorDisplay;

import com.google.gwt.user.client.ui.HasText;

public interface InfoEditorDisplay extends ContentTypeEditorDisplay {

    HasText getDescription();

    HasText getBokTitle();

}
