package net.boklab.document.client.info;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class SwitchWidget extends Composite {
    private FlowPanel container;

    public SwitchWidget() {
	initWidget(container = new FlowPanel());
    }

    @Override
    public void setWidget(final Widget widget) {
	container.add(widget);
    }
}
