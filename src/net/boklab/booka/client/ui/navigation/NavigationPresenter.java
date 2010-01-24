package net.boklab.booka.client.ui.navigation;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NavigationPresenter extends AbstractPresenter<NavigationDisplay> {
    @Inject
    public NavigationPresenter(final EventBus eventBus, Sessions sessions,
	    Provider<NavigationDisplay> displayProvider) {
	super(displayProvider);

	String[] names = new String[] { NavigationDisplay.CONTACT, NavigationDisplay.ACCOUNT,
		NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA, NavigationDisplay.CALENDAR,
		NavigationDisplay.EDITION, NavigationDisplay.ENTRANCE, NavigationDisplay.LOGIN };

	NavigationDisplay display = getDisplay();
	setLoggedIn(false);

	for (final String name : names) {
	    display.getLink(name).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    Place place = new Place(name);
		    eventBus.fireEvent(new PlaceRequestEvent(place));
		}
	    });
	}

	sessions.onLoggedIn(new LoggedInHandler() {
	    @Override
	    public void onLoggedIn(LoggedInEvent event) {
		setLoggedIn(true);
	    }
	});
    }

    private NavigationDisplay setLoggedIn(boolean loggedIn) {
	NavigationDisplay display = getDisplay();
	display.setVisible(NavigationDisplay.ARCHIVES, loggedIn);
	display.setVisible(NavigationDisplay.EDITION, loggedIn);
	display.setVisible(NavigationDisplay.BOOKA, loggedIn);

	display.setVisible(NavigationDisplay.ACCOUNT, loggedIn);
	display.setVisible(NavigationDisplay.CALENDAR, loggedIn);
	display.setVisible(NavigationDisplay.LOGIN, !loggedIn);
	return display;
    }
}
