package net.boklab.core.client.ui.action;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ActionWidget extends Label implements Display, ActionDisplay {
    public ActionWidget(final Action action) {
	addStyleName("bk-Action");
	final String style = action.getIconStyle();
	if (style != null) {
	    addStyleName(action.getIconStyle());
	} else {
	    setText(action.getName());
	}
	setTitle(action.getName());
	setVisible(action.isVisible());
	action.setDisplay(this);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
