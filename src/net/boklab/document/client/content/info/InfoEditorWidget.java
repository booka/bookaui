package net.boklab.document.client.content.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class InfoEditorWidget extends Composite implements InfoEditorDisplay {

    interface DocInfoEditorWidgetUiBinder extends UiBinder<Widget, InfoEditorWidget> {
    }

    private static DocInfoEditorWidgetUiBinder uiBinder = GWT.create(DocInfoEditorWidgetUiBinder.class);

    @UiField
    TextBox title;

    @UiField
    TextArea description;

    public InfoEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getBokTitle() {
	return title;
    }

    @Override
    public HasText getDescription() {
	return description;
    }

}
