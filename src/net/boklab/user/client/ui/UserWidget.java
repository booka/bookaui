package net.boklab.user.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserWidget extends FlowPanel implements UserDisplay {

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setLogin(final Display display) {
	add(display.asWidget());
    }

}
