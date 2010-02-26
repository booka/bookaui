package net.boklab.document.client.content.editor;

import net.boklab.document.client.content.ContentTypeEditorDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ClipEditorWidget extends Composite implements ClipEditorDisplay {

    interface ClipEditorWidgetUiBinder extends UiBinder<Widget, ClipEditorWidget> {
    }

    private static ClipEditorWidgetUiBinder uiBinder = GWT.create(ClipEditorWidgetUiBinder.class);

    @UiField
    SimplePanel editor;
    @UiField
    Anchor save, cancel;

    public ClipEditorWidget() {
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
    public HasClickHandlers getSave() {
	return save;
    }

    @Override
    public void setEditor(final ContentTypeEditorDisplay display) {
	editor.setWidget(display.asWidget());
    }

}
