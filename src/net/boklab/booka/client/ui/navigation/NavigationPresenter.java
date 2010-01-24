package net.boklab.booka.client.ui.navigation;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NavigationPresenter extends AbstractPresenter<NavigationDisplay> {
    private final NavigationDisplay navigation;

    private boolean hasProject;

    final static String[] NAMES = new String[] { NavigationDisplay.CONTACT,
	    NavigationDisplay.ACCOUNT, NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA,
	    NavigationDisplay.CALENDAR, NavigationDisplay.EDITION, NavigationDisplay.ENTRANCE,
	    NavigationDisplay.LOGIN };

    @Inject
    public NavigationPresenter(final EventBus eventBus, final Sessions sessions, Projects projects,
	    Provider<NavigationDisplay> displayProvider) {
	super(displayProvider);
	navigation = getDisplay();

	hasProject = false;

	showIcons(false, hasProject);

	for (final String name : NAMES) {
	    navigation.getLink(name).addClickHandler(new ClickHandler() {
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
		showIcons(true, hasProject);
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(Project project) {
		hasProject = true;
		showIcons(sessions.isLoggedIn(), hasProject);
	    }
	});

    }

    private NavigationDisplay showIcons(boolean loggedIn, boolean hasProject) {
	navigation.setVisible(NavigationDisplay.ARCHIVES, hasProject);
	navigation.setVisible(NavigationDisplay.EDITION, hasProject);
	navigation.setVisible(NavigationDisplay.BOOKA, hasProject);

	navigation.setVisible(NavigationDisplay.ACCOUNT, loggedIn);
	navigation.setVisible(NavigationDisplay.CALENDAR, loggedIn);
	navigation.setVisible(NavigationDisplay.LOGIN, !loggedIn);
	return navigation;
    }
}
