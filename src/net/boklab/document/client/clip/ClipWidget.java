package net.boklab.document.client.clip;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ClipWidget extends Composite implements ClipDisplay {

    interface ClipWidgetUiBinder extends UiBinder<Widget, ClipWidget> {
    }

    private static ClipWidgetUiBinder uiBinder = GWT.create(ClipWidgetUiBinder.class);

    @UiField
    HTML body;
    @UiField
    FocusPanel view;
    @UiField
    SimplePanel controls;

    public ClipWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public boolean areControls() {
	return controls.getWidget() != null;
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
    public void setControls(final Display display) {
	controls.clear();
	if (display != null) {
	    controls.setWidget(display.asWidget());
	}
    }

    @Override
    public void setViewVisible(final boolean visible) {
	view.setVisible(visible);
    }

}
