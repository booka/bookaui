package net.boklab.booka.client.ui.navigation;

import java.util.HashMap;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NavigationWidget extends Composite implements NavigationDisplay {

    interface NavigationWidgetUiBinder extends UiBinder<Widget, NavigationWidget> {
    }

    private static final String ENTRANCE = "entrance";

    private static NavigationWidgetUiBinder uiBinder = GWT.create(NavigationWidgetUiBinder.class);

    @UiField
    Anchor entrance, archives, edition, booka, calendar, account, contact, login;

    private final HashMap<String, Anchor> items;

    @Inject
    public NavigationWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	this.items = new HashMap<String, Anchor>();
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
    public HasClickHandlers getLink(String name) {
	return items.get(name);
    }

    @Override
    public Set<String> getLinkNames() {
	return items.keySet();
    }

    @Override
    public void setVisible(String item, boolean visible) {
	items.get(item).setVisible(visible);
    }

}
