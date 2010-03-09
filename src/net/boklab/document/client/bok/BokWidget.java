package net.boklab.document.client.bok;

import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BokWidget extends Composite implements BokDisplay {

    interface ClipWidgetUiBinder extends UiBinder<Widget, BokWidget> {
    }

    private static ClipWidgetUiBinder uiBinder = GWT.create(ClipWidgetUiBinder.class);

    @UiField
    HTML body;
    @UiField
    FocusPanel view, self;
    @UiField
    SimplePanel controls;
    @UiField
    Label waiting;
    @UiField
    Anchor insertBefore, insertAfter;

    public BokWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	setWaitingVisible(false);
	waiting.addStyleName(Icons.get(BokIcon.loading));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getActive() {
	return view;
    }

    @Override
    public HasHTML getBody() {
	return body;
    }

    @Override
    public HasClickHandlers getInsertAfter() {
	return insertAfter;
    }

    @Override
    public HasClickHandlers getInsertBefore() {
	return insertBefore;
    }

    @Override
    public HasMouseOutHandlers getMouseOut() {
	return self;
    }

    @Override
    public HasMouseOverHandlers getMouseOver() {
	return self;
    }

    @Override
    public boolean hasControls() {
	return controls.getWidget() != null;
    }

    @Override
    public void setControls(final Display display) {
	controls.clear();
	if (display != null) {
	    controls.setWidget(display.asWidget());
	}
    }

    @Override
    public void setSlotsVisible(final boolean visible) {
	insertBefore.setVisible(visible);
	insertAfter.setVisible(visible);
    }

    @Override
    public void setViewVisible(final boolean visible) {
	view.setVisible(visible);
    }

    @Override
    public void setWaitingVisible(final boolean visible) {
	waiting.setVisible(visible);
    }

}
