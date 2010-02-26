package net.boklab.document.client.clip.editor;

import net.boklab.document.client.content.ContentEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
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

    private ContentEditor<?> editorPresenter;

    public ClipEditorWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getCancel() {
	return cancel;
    }

    @Override
    public HasClickHandlers getCancelAction() {
	return cancel;
    }

    @Override
    public ContentEditor<?> getEditor() {
	return editorPresenter;
    }

    @Override
    public HasText getSave() {
	return save;
    }

    @Override
    public HasClickHandlers getSaveAction() {
	return save;
    }

    @Override
    public void setEditor(final ContentEditor<?> editorPresenter) {
	this.editorPresenter = editorPresenter;
	editor.setWidget(editorPresenter.getDisplay().asWidget());
    }

    @Override
    public void setSaveVisible(final boolean visible) {
	save.setVisible(visible);
    }

}
