package net.boklab.module.explore.client.pointer.editor;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface PointerEditorDisplay extends Display {

    HasClickHandlers getCancel();

    HasText getPointerTitle();

    HasText getPosition();

    HasClickHandlers getSave();

}
