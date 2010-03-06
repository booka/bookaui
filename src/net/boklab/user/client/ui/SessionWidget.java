package net.boklab.user.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class SessionWidget extends FlowPanel implements SessionDisplay {

    @Override
    public void addDisplay(final Display display) {
	add(display.asWidget());
	setStyleName("bk-Session");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void removeDisplay(final Display display) {
	clear();
    }

}
