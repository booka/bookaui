package net.boklab.document.client.bok.insert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SelectContentWidget extends Composite implements SelectContentDisplay {

    interface SelectContentWidgetUiBinder extends UiBinder<Widget, SelectContentWidget> {
    }

    private static SelectContentWidgetUiBinder uiBinder = GWT.create(SelectContentWidgetUiBinder.class);

    @UiField
    Anchor accept, cancel;

    public SelectContentWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getAccept() {
	return null;
    }

    @Override
    public HasClickHandlers getCancel() {
	return null;
    }

}
