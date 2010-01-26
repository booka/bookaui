package net.boklab.document.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class DocumentWidget extends Composite implements DocumentDisplay {

    interface DocumentWidgetUiBinder extends UiBinder<Widget, DocumentWidget> {
    }

    private static DocumentWidgetUiBinder uiBinder = GWT.create(DocumentWidgetUiBinder.class);

    @UiField
    FlowPanel content, page;

    private Display infoDisplay;

    public DocumentWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasWidgets getContents() {
	return content;
    }

    @Override
    public void setInfoDisplay(Display infoDisplay) {
	assert infoDisplay != null : "Info display can't be null";
	assert this.infoDisplay == null : "Only one info display by document";

	this.infoDisplay = infoDisplay;
	page.insert(infoDisplay.asWidget(), page.getWidgetIndex(content));
    }
}
