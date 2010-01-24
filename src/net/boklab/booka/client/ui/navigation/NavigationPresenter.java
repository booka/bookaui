package net.boklab.booka.client.ui.navigation;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class NavigationPresenter extends AbstractPresenter<NavigationDisplay> {
    @Inject
    public NavigationPresenter(final EventBus eventBus, NavigationDisplay display) {
	super(display);

	String[] names = new String[] { NavigationDisplay.CONTACT, NavigationDisplay.ACCOUNT,
		NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA, NavigationDisplay.CALENDAR,
		NavigationDisplay.EDITION, NavigationDisplay.ENTRANCE, NavigationDisplay.LOGIN };

	display.setVisible(NavigationDisplay.ARCHIVES, false);
	display.setVisible(NavigationDisplay.EDITION, false);
	display.setVisible(NavigationDisplay.BOOKA, false);

	display.setVisible(NavigationDisplay.ACCOUNT, false);
	display.setVisible(NavigationDisplay.CALENDAR, false);

	for (final String name : names) {
	    display.getLink(name).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    Place place = new Place(name);
		    eventBus.fireEvent(new PlaceRequestEvent(place));
		}
	    });
	}
    }
}
