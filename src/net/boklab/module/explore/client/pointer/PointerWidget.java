package net.boklab.module.explore.client.pointer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PointerWidget extends Composite implements PointerDisplay {

    interface PointerWidgetUiBinder extends UiBinder<Widget, PointerWidget> {
    }

    private static PointerWidgetUiBinder uiBinder = GWT.create(PointerWidgetUiBinder.class);

    @UiField
    Label position, title;
    @UiField
    FocusPanel self;

    public PointerWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public Label getPointerTitle() {
	return title;
    }

    @Override
    public Label getPosition() {
	return position;
    }

    @Override
    public HasClickHandlers getSelf() {
	return self;
    }
}
