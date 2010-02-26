package net.boklab.document.client.content.html;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class HtmlEditorWidget extends Composite implements HtmlEditorDisplay {

    interface HtmlEditorWidgetUiBinder extends UiBinder<Widget, HtmlEditorWidget> {
    }

    private static HtmlEditorWidgetUiBinder uiBinder = GWT.create(HtmlEditorWidgetUiBinder.class);

    @UiField
    TextArea body;

    public HtmlEditorWidget() {
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

}
