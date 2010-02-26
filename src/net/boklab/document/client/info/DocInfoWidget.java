package net.boklab.document.client.info;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.Widget;

public class DocInfoWidget extends SwitchWidget implements DocInfoDisplay {

    public DocInfoWidget() {
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setDisplay(final Display display) {
	setWidget(display.asWidget());
    }

}
