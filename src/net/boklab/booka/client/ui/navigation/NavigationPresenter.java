package net.boklab.booka.client.ui.navigation;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NavigationPresenter extends AbstractPresenter<NavigationDisplay> {
    private final NavigationDisplay navigation;

    final static String[] NAMES = new String[] { NavigationDisplay.CONTACT, NavigationDisplay.ACCOUNT,
	    NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA, NavigationDisplay.CALENDAR, NavigationDisplay.EDITION,
	    NavigationDisplay.ENTRANCE, NavigationDisplay.LOGIN };

    @Inject
    public NavigationPresenter(final EventBus eventBus, final Sessions sessions, final ProjectManager projects,
	    final Provider<NavigationDisplay> displayProvider) {
	super(displayProvider);
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
	    public void onProject(final Project project) {
		showIcons(sessions.isLoggedIn(), projects.hasActiveProject());
	    }
	});

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
