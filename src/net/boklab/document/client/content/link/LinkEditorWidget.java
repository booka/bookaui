package net.boklab.document.client.content.link;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LinkEditorWidget extends Composite implements LinkEditorDisplay {

    interface LunkEditorWidgetUiBinder extends UiBinder<Widget, LinkEditorWidget> {
    }

    private static LunkEditorWidgetUiBinder uiBinder = GWT.create(LunkEditorWidgetUiBinder.class);

    @UiField
    TextBox title;
    @UiField
    TextArea body;
    @UiField
    Button previewAction;
    @UiField
    DivElement preview;

    public LinkEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getBody() {
	return body;
    }

    @Override
    public HasText getBokTitle() {
	return title;
    }

    @Override
    public HasClickHandlers getPreviewAction() {
	return previewAction;
    }

    @Override
    public void setPreviewHtml(final String rendered) {
	preview.setInnerHTML(rendered);
    }
}
