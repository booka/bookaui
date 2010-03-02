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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NavigationPresenter implements Presenter<NavigationDisplay> {
    final static String[] NAMES = new String[] { NavigationDisplay.CONTACT, NavigationDisplay.ACCOUNT,
	    NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA, NavigationDisplay.CALENDAR, NavigationDisplay.EDITION,
	    NavigationDisplay.ENTRANCE, NavigationDisplay.LOGIN };

    private final NavigationDisplay display;

    @Inject
    public NavigationPresenter(final EventBus eventBus, final Sessions sessions, final ProjectManager projects,
	    final NavigationDisplay display) {
	this.display = display;

	GWT.log("NAVI PRESENTER");

	setProjectIconsVisible(projects.hasActiveProject());
	setUserIconsVisible(false);

	for (final String name : NAMES) {
	    display.getLink(name).addClickHandler(new ClickHandler() {
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
		setUserIconsVisible(true);
		setUser(event.getUserSession().getUserName());
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(final ProjectOpenedEvent event) {
		setProjectIconsVisible(projects.hasActiveProject());
	    }
	});

	setUserIconsVisible(sessions.isLoggedIn());
	setUser(sessions.getUserName());
	setProjectIconsVisible(projects.hasActiveProject());
    }

    @Override
    public NavigationDisplay getDisplay() {
	return display;
    }

    public void setMessage(final String message) {
	display.getMessage().setText(message);
    }

    public void setPlace(final String placeDescription) {
	display.getPlace().setText(placeDescription);
    }

    public void setProject(final String projectName) {
	display.getProject().setText(projectName);
    }

    private void setProjectIconsVisible(final boolean hasProject) {
	display.setVisible(NavigationDisplay.ARCHIVES, hasProject);
	display.setVisible(NavigationDisplay.EDITION, hasProject);
	display.setVisible(NavigationDisplay.BOOKA, hasProject);
    }

    private void setUserIconsVisible(final boolean loggedIn) {
	display.setVisible(NavigationDisplay.ACCOUNT, loggedIn);
	display.setVisible(NavigationDisplay.CALENDAR, loggedIn);
	display.setVisible(NavigationDisplay.LOGIN, !loggedIn);
    }

    protected void setUser(final String userName) {
	display.getUser().setText(userName);
    }
}
