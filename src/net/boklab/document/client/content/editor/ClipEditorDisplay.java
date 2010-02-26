package net.boklab.document.client.content.editor;

import net.boklab.document.client.content.ContentTypeEditorDisplay;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ClipEditorDisplay extends Display {

    HasClickHandlers getCancel();

    HasClickHandlers getSave();

    void setEditor(ContentTypeEditorDisplay display);

}
