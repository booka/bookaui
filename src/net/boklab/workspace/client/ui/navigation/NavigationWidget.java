package net.boklab.workspace.client.ui.navigation;

import java.util.HashMap;

import net.boklab.workspace.client.ui.signals.SignalsDisplay;
import net.boklab.workspace.client.ui.signals.SignalsWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
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
    SignalsWidget signals;

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
    public SignalsDisplay getSignals() {
	return signals;
    }

    @Override
    public void setLinkActive(final String item, final boolean active) {
	if (active) {
	    items.get(item).addStyleName("active");
	} else {
	    items.get(item).removeStyleName("active");
	}
    }

    @Override
    public void setVisible(final String item, final boolean visible) {
	items.get(item).setVisible(visible);
    }

}
