package net.boklab.tools.client.mvp;

import com.google.gwt.user.client.ui.Widget;

public interface Display {

    public static final Display NONE = new Display() {
	@Override
	public Widget asWidget() {
	    return null;
	}
    };

    Widget asWidget();
}
