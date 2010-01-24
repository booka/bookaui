package net.boklab.document.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DocumentWidget extends Composite implements DocumentDisplay {

    interface DocumentWidgetUiBinder extends UiBinder<Widget, DocumentWidget> {
    }

    private static DocumentWidgetUiBinder uiBinder = GWT.create(DocumentWidgetUiBinder.class);

    @UiField
    FlowPanel content;

    @UiField
    Label title, description;

    public DocumentWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasWidgets getContent() {
	return content;
    }

    @Override
    public HasText getDocumentDescription() {
	return description;
    }

    @Override
    public HasText getDocumentTitle() {
	return title;
    }

}
