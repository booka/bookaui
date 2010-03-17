package net.boklab.module.archives.client.archive.browser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ArchiveItemWidget extends Composite implements ArchiveItemDisplay {

    interface DocumentItemWidgetUiBinder extends UiBinder<Widget, ArchiveItemWidget> {
    }

    private static DocumentItemWidgetUiBinder uiBinder = GWT
	    .create(DocumentItemWidgetUiBinder.class);

    @UiField
    Label title;

    @UiField
    HTML description, extra;

    public ArchiveItemWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getClickeable() {
	return title;
    }

    @Override
    public HasHTML getDescription() {
	return description;
    }

    @Override
    public HasText getDocumentTitle() {
	return title;
    }

    @Override
    public HasHTML getExtra() {
	return extra;
    }

}
