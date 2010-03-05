package net.boklab.indices.client.browser;

import net.boklab.core.client.ui.action.Action;
import net.boklab.core.client.ui.action.ActionWidget;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class BrowserWidget extends Composite implements BrowserDisplay {

    interface IndiceWidgetUiBinder extends UiBinder<Widget, BrowserWidget> {
    }

    private static IndiceWidgetUiBinder uiBinder = GWT.create(IndiceWidgetUiBinder.class);

    @UiField
    Label title;

    @UiField
    FlowPanel list, actions;

    public BrowserWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public ActionWidget addAction(final Action<?> action) {
	return new ActionWidget(action);
    }

    @Override
    public void addItem(final Display display) {
	list.add(display.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearList() {
	list.clear();
    }

    @Override
    public HasText getBrowserTitle() {
	return title;
    }

}
