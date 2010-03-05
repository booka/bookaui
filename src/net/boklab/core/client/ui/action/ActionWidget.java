package net.boklab.core.client.ui.action;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ActionWidget extends Label implements ActionDisplay {
    public ActionWidget(final Action<?> action) {
	addStyleName("hablar-ActionWidget");
	addStyleName(action.getIconStyle());
	this.ensureDebugId(action.getId());
	setTitle(action.getName());
    }

    @Override
    public Widget asWidget() {
	return this;
    }
}
