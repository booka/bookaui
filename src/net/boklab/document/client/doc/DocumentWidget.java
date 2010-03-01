package net.boklab.document.client.doc;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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
    public void add(final Display display) {
	content.add(display.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clear() {
	content.clear();
    }

    @Override
    public int getBokCount() {
	return content.getWidgetCount();
    }

    @Override
    public int getDisplayIndex(final Display display) {
	return content.getWidgetIndex(display.asWidget());
    }

    @Override
    public void insert(final Display display, final int beforeIndex) {
	content.insert(display.asWidget(), beforeIndex);
    }

    @Override
    public void remove(final Display display) {
	content.remove(display.asWidget());
    }

    @Override
    public void setInfoDisplay(final Display infoDisplay) {
	assert infoDisplay != null : "Info display can't be null";
	assert this.infoDisplay == null : "Only one info display by document";

	this.infoDisplay = infoDisplay;
	page.insert(infoDisplay.asWidget(), page.getWidgetIndex(content));
    }
}
