package net.boklab.browser.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentBrowserWidget extends Composite implements DocumentBrowserDisplay {

    interface DocumentBrowserWidgetUiBinder extends UiBinder<Widget, DocumentBrowserWidget> {
    }

    private static DocumentBrowserWidgetUiBinder uiBinder = GWT
	    .create(DocumentBrowserWidgetUiBinder.class);

    @UiField
    FlowPanel list;

    @UiField
    Anchor create;

    private final Provider<DocumentItemPresenter> provider;

    @Inject
    public DocumentBrowserWidget(Provider<DocumentItemPresenter> provider) {
	this.provider = provider;
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public DocumentItemPresenter createItem() {
	return provider.get();
    }

    @Override
    public HasClickHandlers getCreate() {
	return create;
    }

    @Override
    public HasWidgets getList() {
	return list;
    }

    @Override
    public void setCreateVisible(boolean visible) {
	create.setVisible(visible);
    }

}
