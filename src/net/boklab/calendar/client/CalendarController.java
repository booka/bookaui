package net.boklab.calendar.client;

import net.boklab.calendar.client.ui.CalendarPresenter;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CalendarController {

    @Inject
    public CalendarController(final Router router, final Provider<BookaAppPresenter> bookaProvider,
	    final NavigationPresenter navigation, final Provider<CalendarPresenter> calendarProvider) {

	final String CALENDAR = "calendario";
	navigation.registerResource(NavigationDisplay.CALENDAR, CALENDAR);

	router.onRequest(Paths.singletonResource(CALENDAR), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		bookaProvider.get().setContent(calendarProvider.get());
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.CALENDAR);
	    }
	});
    }
}
