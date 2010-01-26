package net.boklab.document.client.info;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class SwitchWidget extends Composite {
    private FlowPanel container;

    public SwitchWidget() {
	initWidget(container = new FlowPanel());
    }
}
