package net.boklab.document.client.info.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DocInfoEditorWidget extends Composite {

    interface DocInfoEditorWidgetUiBinder extends UiBinder<Widget, DocInfoEditorWidget> {
    }

    private static DocInfoEditorWidgetUiBinder uiBinder = GWT
	    .create(DocInfoEditorWidgetUiBinder.class);

    @UiField
    TextBox title, description;
    @UiField
    Button save, cancel;

    public DocInfoEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

}
