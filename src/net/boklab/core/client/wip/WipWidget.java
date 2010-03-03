package net.boklab.core.client.wip;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WipWidget extends FlowPanel implements WipDisplay {

    public WipWidget() {
	setStyleName("bk-Wip");
	add(new Label("Work in progress...."));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
