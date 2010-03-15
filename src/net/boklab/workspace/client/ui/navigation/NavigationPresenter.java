package net.boklab.workspace.client.ui.navigation;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.ui.overlay.OverlayPresenter;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.workspace.client.msg.CreateMessageEvent;
import net.boklab.workspace.client.msg.CreateMessageHandler;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.msg.RemoveMessageEvent;
import net.boklab.workspace.client.msg.RemoveMessageHandler;
import net.boklab.workspace.client.msg.CreateMessageEvent.Level;
import net.boklab.workspace.client.ui.signals.SignalsDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NavigationPresenter implements Presenter<NavigationDisplay> {

    final static String[] NAMES = new String[] { NavigationDisplay.CONTACT,
	    NavigationDisplay.ACCOUNT, NavigationDisplay.ARCHIVES, NavigationDisplay.BOOKA,
	    NavigationDisplay.CALENDAR, NavigationDisplay.EDITION, NavigationDisplay.ENTRANCE,
	    NavigationDisplay.LOGIN };

    private final NavigationDisplay display;
    private final SignalsDisplay signals;
    private final EventBus eventBus;
    private String currentActive;

    @Inject
    public NavigationPresenter(final EventBus eventBus, final MessageManager messages,
	    final OverlayPresenter overlay, final ProjectManager projects,
	    final NavigationDisplay display) {
	this.eventBus = eventBus;
	this.display = display;

	signals = display.getSignals();

	for (final String name : NAMES) {
	    display.getLink(name).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    eventBus.fireEvent(new NavigationEvent(name));
		}
	    });
	}

	signals.getUserAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		GWT.log("NAVPRE user action");
		eventBus.fireEvent(new PlaceRequestEvent(new Place(SignalsDisplay.USER)));
	    }
	});

	messages.addCreateMessageHandler(new CreateMessageHandler() {
	    @Override
	    public void onAddMessage(final CreateMessageEvent event) {
		if (event.getLevel() == Level.error) {
		    overlay.showError(event.getMessage());
		}
		addMessage(event.getId(), event.getMessage(), event.getLevel());
	    }
	});

	messages.addRemoveMessageHandler(new RemoveMessageHandler() {
	    @Override
	    public void onRemoveMessage(final RemoveMessageEvent event) {
		signals.removeMessage(event.getId());
	    }
	});

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		GWT.log("NAVPRE project opened" + projects.hasActive());
		setProjectIconsVisible(projects.hasActive());
	    }
	});
	setProjectIconsVisible(projects.hasActive());

    }

    public void addNavigationHandler(final NavigationHandler handler) {
	eventBus.addHandler(NavigationEvent.getType(), handler);
    }

    @Override
    public void bind() {
    }

    @Override
    public NavigationDisplay getDisplay() {
	return display;
    }

    public void setActiveIcon(final String active) {
	if (currentActive != null) {
	    display.setLinkActive(currentActive, false);
	}
	currentActive = active;
	if (active != null) {
	    display.setLinkActive(active, true);
	}
    }

    public void setCurrentLocation(final String placeDescription) {
	GWT.log("NAV LOCATION: " + placeDescription);
	signals.getPlace().setText(placeDescription);
    }

    public void setProjectName(final String projectName) {
	signals.getProject().setText(projectName);
    }

    public void setUser(final String userName, final boolean active) {
	signals.getUser().setText(userName);
	signals.setUserActive(active);
    }

    public void setUserIconsVisible(final boolean loggedIn) {
	display.setVisible(NavigationDisplay.ACCOUNT, loggedIn);
	display.setVisible(NavigationDisplay.CALENDAR, loggedIn);
	display.setVisible(NavigationDisplay.LOGIN, !loggedIn);
    }

    private void addMessage(final int id, final String message, final Level level) {
	signals.addMessage(id, message, level.toString());
    }

    private void setProjectIconsVisible(final boolean hasProject) {
	display.setVisible(NavigationDisplay.ARCHIVES, hasProject);
	display.setVisible(NavigationDisplay.EDITION, hasProject);
	display.setVisible(NavigationDisplay.BOOKA, hasProject);
    }

}
