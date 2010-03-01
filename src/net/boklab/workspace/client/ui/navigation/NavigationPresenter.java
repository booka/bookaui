package net.boklab.workspace.client.ui.navigation;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.action.ProjectOpenedEvent;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class NavigationPresenter implements Presenter<NavigationDisplay> {
    private final NavigationDisplay navigation;

    final static String[] NAMES = new String[] { NavigationDisplay.CONTACT, NavigationDisplay.ACCOUNT,
	    NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA, NavigationDisplay.CALENDAR, NavigationDisplay.EDITION,
	    NavigationDisplay.ENTRANCE, NavigationDisplay.LOGIN };

    private final NavigationDisplay display;

    @Inject
    public NavigationPresenter(final EventBus eventBus, final Sessions sessions, final ProjectManager projects,
	    final NavigationDisplay display) {
	this.display = display;
	navigation = getDisplay();

	showIcons(false, projects.hasActiveProject());

	for (final String name : NAMES) {
	    navigation.getLink(name).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    Log.debug("Navigation:" + name);
		    final Place place = new Place(name);
		    eventBus.fireEvent(new PlaceRequestEvent(place));
		}
	    });
	}

	sessions.onLoggedIn(new LoggedInHandler() {
	    @Override
	    public void onLoggedIn(final LoggedInEvent event) {
		showIcons(true, projects.hasActiveProject());
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(final ProjectOpenedEvent event) {
		showIcons(sessions.isLoggedIn(), projects.hasActiveProject());
	    }
	});

    }

    @Override
    public NavigationDisplay getDisplay() {
	return display;
    }

    public void setMessage(final String message) {
	display.getMessage().setText(message);
    }

    private NavigationDisplay showIcons(final boolean loggedIn, final boolean hasProject) {
	navigation.setVisible(NavigationDisplay.ARCHIVES, hasProject);
	navigation.setVisible(NavigationDisplay.EDITION, hasProject);
	navigation.setVisible(NavigationDisplay.BOOKA, hasProject);

	navigation.setVisible(NavigationDisplay.ACCOUNT, loggedIn);
	navigation.setVisible(NavigationDisplay.CALENDAR, loggedIn);
	navigation.setVisible(NavigationDisplay.LOGIN, !loggedIn);
	return navigation;
    }
}
