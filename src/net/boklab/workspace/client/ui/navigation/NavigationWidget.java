package net.boklab.workspace.client.ui.navigation;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NavigationWidget extends Composite implements NavigationDisplay {

    interface NavigationWidgetUiBinder extends UiBinder<Widget, NavigationWidget> {
    }

    private static NavigationWidgetUiBinder uiBinder = GWT.create(NavigationWidgetUiBinder.class);

    @UiField
    Anchor entrance, archives, edition, booka, calendar, account, contact, login;

    @UiField
    Label place, message, project, user;

    private final HashMap<String, Anchor> items;

    @Inject
    public NavigationWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	items = new HashMap<String, Anchor>();
	items.put(ENTRANCE, entrance);
	items.put(ARCHIVES, archives);
	items.put(EDITION, edition);
	items.put(BOOKA, booka);
	items.put(CALENDAR, calendar);
	items.put(ACCOUNT, account);
	items.put(CONTACT, contact);
	items.put(LOGIN, login);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getLink(final String name) {
	return items.get(name);
    }

    @Override
    public HasText getMessage() {
	return message;
    }

    @Override
    public HasText getPlace() {
	return place;
    }

    @Override
    public HasText getProject() {
	return project;
    }

    @Override
    public HasText getUser() {
	return user;
    }

    @Override
    public void setVisible(final String item, final boolean visible) {
	items.get(item).setVisible(visible);
    }

}
