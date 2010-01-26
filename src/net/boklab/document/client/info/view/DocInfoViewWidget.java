package net.boklab.document.client.info.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DocInfoViewWidget extends Composite implements DocInfoViewDisplay {

    interface DocInfoViewWidgetUiBinder extends UiBinder<Widget, DocInfoViewWidget> {
    }
    @UiField
    Label title, description;

    private static DocInfoViewWidgetUiBinder uiBinder = GWT.create(DocInfoViewWidgetUiBinder.class);

    public DocInfoViewWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getDocumentDescription() {
	return description;
    }

    @Override
    public HasText getDocumentTitle() {
	return title;
    }
}
