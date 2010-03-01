package net.boklab.calendar.client;

import net.boklab.calendar.client.ui.CalendarPresenter;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BokCalendarInstaller {

    @Inject
    public BokCalendarInstaller(final Router router, final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<CalendarPresenter> calendarProvider) {

	router.onRequest("^/calendar$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		bookaProvider.get().setContent(calendarProvider.get());
		router.fireChanged(I18nCalendar.t.placeCalendar(), event.getPlace());
	    }
	});
    }
}
