package net.boklab.document.client.bok.editor;

import net.boklab.document.client.content.ContentEditor;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface BokEditorDisplay extends Display {

    HasText getCancel();

    HasClickHandlers getCancelAction();

    ContentEditor<?> getEditor();

    HasText getSave();

    HasClickHandlers getSaveAction();

    void setEditor(ContentEditor<?> editor);

    void setSaveVisible(boolean visible);

}
