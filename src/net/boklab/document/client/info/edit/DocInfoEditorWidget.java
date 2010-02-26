package net.boklab.document.client.info.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DocInfoEditorWidget extends Composite implements DocInfoEditorDisplay {

    interface DocInfoEditorWidgetUiBinder extends UiBinder<Widget, DocInfoEditorWidget> {
    }

    private static DocInfoEditorWidgetUiBinder uiBinder = GWT.create(DocInfoEditorWidgetUiBinder.class);

    @UiField
    TextBox title;

    @UiField
    TextArea description;
    @UiField
    Anchor save, cancel;

    public DocInfoEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getCancel() {
	return cancel;
    }

    @Override
    public HasText getDocumentDescription() {
	return description;
    }

    @Override
    public HasText getDocumentTitle() {
	return title;
    }

    @Override
    public HasClickHandlers getSave() {
	return save;
    }

}
