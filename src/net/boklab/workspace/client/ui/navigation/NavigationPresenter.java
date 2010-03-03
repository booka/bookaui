package net.boklab.workspace.client.ui.navigation;

import java.util.HashMap;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.action.ProjectOpenedEvent;
import net.boklab.site.client.action.ProjectOpenedHandler;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceChangedHandler;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.workspace.client.event.UserMessageEvent;
import net.boklab.workspace.client.event.UserMessageHandler;
import net.boklab.workspace.client.event.UserMessageEvent.Level;
import net.boklab.workspace.client.ui.signals.SignalsDisplay;

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
    private final SignalsDisplay signals;
    private final HashMap<String, String> iconToResources;
    private final EventBus eventBus;

    @Inject
    public NavigationPresenter(final EventBus eventBus, final Sessions sessions, final ProjectManager projects,
	    final NavigationDisplay display) {
	this.eventBus = eventBus;
	this.display = display;

	signals = display.getSignals();

	iconToResources = new HashMap<String, String>();

	setProjectIconsVisible(projects.hasActiveProject());
	setUserIconsVisible(false);

	for (final String name : NAMES) {
	    display.getLink(name).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    final String resourceName = iconToResources.get(name);
		    if (resourceName != null) {
			eventBus.fireEvent(new PlaceRequestEvent(new Place(resourceName)));
		    } else {
			GWT.log("** NO RESOURCE: " + name);
		    }
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

	eventBus.addHandler(UserMessageEvent.getType(), new UserMessageHandler() {
	    @Override
	    public void onUserMessage(final UserMessageEvent event) {
		addMessage(event.getId(), event.getMessage(), event.getLevel());
	    }
	});

	eventBus.addHandler(PlaceChangedEvent.getType(), new PlaceChangedHandler() {
	    @Override
	    public void onPlaceChanged(final PlaceChangedEvent event) {
		setPlace(event.getDescription());
	    }
	});

	eventBus.addHandler(PlaceRequestEvent.getType(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		setPlace("");
	    }
	});

	setUserIconsVisible(sessions.isLoggedIn());
	setUser(sessions.getUserName());
	setProjectIconsVisible(projects.hasActiveProject());
    }

    public int fireUserMessage(final String message, final Level level) {
	final UserMessageEvent event = new UserMessageEvent(message, level);
	eventBus.fireEvent(event);
	return event.getId();
    }

    @Override
    public NavigationDisplay getDisplay() {
	return display;
    }

    public void removeMessage(final Integer id) {
	signals.removeMessage(id);
    }

    public void setPlace(final String placeDescription) {
	signals.getPlace().setText(placeDescription);
    }

    public void setProject(final String projectName) {
	signals.getProject().setText(projectName);
    }

    public void setResource(final String name, final String resource) {
	iconToResources.put(name, resource);
    }

    public void setUser(final String userName) {
	signals.getUser().setText(userName);
    }

    private void addMessage(final int id, final String message, final Level level) {
	signals.addMessage(id, message, level.toString());
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

}
