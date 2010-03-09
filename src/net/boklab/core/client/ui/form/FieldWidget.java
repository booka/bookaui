package net.boklab.core.client.ui.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FieldWidget extends Composite implements HasText {

    interface FieldWidgetUiBinder extends UiBinder<Widget, FieldWidget> {
    }

    private static FieldWidgetUiBinder uiBinder = GWT.create(FieldWidgetUiBinder.class);

    @UiField
    Label label, message;
    @UiField
    TextBox field;

    @UiConstructor
    public FieldWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	message.setText("Aquí va el hint");
    }

    public HasText getContent() {
	return field;
    }

    @Override
    public String getText() {
	return label.getText();
    }

    @Override
    public void setText(final String text) {
	label.setText(text);
    }

}
