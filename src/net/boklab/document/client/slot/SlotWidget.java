package net.boklab.document.client.slot;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class SlotWidget extends Composite implements SlotDisplay {

    interface SlotWidgetUiBinder extends UiBinder<Widget, SlotWidget> {
    }

    private static SlotWidgetUiBinder uiBinder = GWT.create(SlotWidgetUiBinder.class);

    @UiField
    FocusPanel self;

    public SlotWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getOpen() {
	return self;
    }

    @Override
    public boolean hasContentTypeList() {
	return self.getWidget() != null;
    }

    @Override
    public void setList(final Display display) {
	if (display != null) {
	    self.setWidget(display.asWidget());
	} else {
	    self.clear();
	}
    }

}
