package net.boklab.module.explore.client.pointer.editor;

import net.boklab.core.client.ui.form.FieldWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class PointerEditorWidget extends Composite implements PointerEditorDisplay {

    interface PointerEditorWidgetUiBinder extends UiBinder<Widget, PointerEditorWidget> {
    }

    @UiField
    Anchor save, cancel;
    @UiField
    FieldWidget position, title;

    private static PointerEditorWidgetUiBinder uiBinder = GWT
	    .create(PointerEditorWidgetUiBinder.class);

    public PointerEditorWidget() {
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
    public HasText getPointerTitle() {
	return title.getContent();
    }

    @Override
    public HasText getPosition() {
	return position.getContent();
    }

    public Anchor getSave() {
	return save;
    }
}
