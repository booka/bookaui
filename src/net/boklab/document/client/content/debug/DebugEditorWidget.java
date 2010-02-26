package net.boklab.document.client.content.debug;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DebugEditorWidget extends Composite implements DebugEditorDisplay {

    interface DebugEditorWidgetUiBinder extends UiBinder<Widget, DebugEditorWidget> {
    }

    private static DebugEditorWidgetUiBinder uiBinder = GWT.create(DebugEditorWidgetUiBinder.class);

    @UiField
    Label info;

    @UiField
    TextBox contentType, position, bokTitle;

    public DebugEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getClipTitle() {
	return bokTitle;
    }

    @Override
    public HasText getContentType() {
	return contentType;
    }

    @Override
    public HasText getInfo() {
	return info;
    }

    @Override
    public HasText getPosition() {
	return position;
    }

}
