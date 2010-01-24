package net.boklab.document.client.ui.clip;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class ClipWidget extends Composite implements ClipDisplay {

    interface ClipWidgetUiBinder extends UiBinder<Widget, ClipWidget> {
    }

    private static ClipWidgetUiBinder uiBinder = GWT.create(ClipWidgetUiBinder.class);

    @UiField
    HTML body;

    public ClipWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasHTML getBody() {
	return body;
    }

}
