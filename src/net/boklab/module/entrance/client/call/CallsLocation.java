package net.boklab.module.entrance.client.call;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.module.Location;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.BokSelectedHandler;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.entrance.client.EntranceWorkspace;
import net.boklab.module.entrance.client.I18nEntrance;
import net.boklab.module.entrance.client.project.ProjectManager;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CallsLocation implements Location {
    private final String callsResource;
    private final CallManager calls;
    private final ClientSession session;
    private final EventBus eventBus;

    @Inject
    public CallsLocation(final EventBus eventBus, final Router router, final ClientSession session,
	    final NavigationPresenter navigation, final Provider<EntranceWorkspace> workspace,
	    final ProjectManager projects, final CallManager calls) {
	this.eventBus = eventBus;
	this.session = session;
	this.calls = calls;

	callsResource = I18nEntrance.t.resourceCalls();

	eventBus.addHandler(BokSelectedEvent.getType(), new BokSelectedHandler() {
	    @Override
	    public void onBokSelected(final BokSelectedEvent event) {
		final Bok bok = event.getBok();
		if (Bok.CALL.equals(bok.getBokType())) {
		    onCallSelected(bok);
		} else if (Bok.PROJECT.equals(bok.getBokType())) {
		    onProjectSelected(bok);
		}
	    }

	    private void onCallSelected(final Bok bok) {
		router.setCurrent(new Place(callsResource, bok.getId()));
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().setDocument(bok);
		workspace.get().show();
		navigation.setCurrentLocation(I18nEntrance.t.locationCall(bok.getTitle()));

		DeferredCommand.addCommand(new Command() {
		    @Override
		    public void execute() {
			if (!projects.isActive(bok.getProjectId())) {
			    projects.open(bok.getProjectId(), bok.getTitle(), null);
			}
		    }
		});
	    }

	    private void onProjectSelected(final Bok bok) {
		projects.open(bok.getId(), bok.getTitle(), new BokOpenedHandler() {
		    @Override
		    public void onBokOpened(final BokOpenedEvent event) {
			final Bok call = calls.openCallOfProject(event.getBok());
			eventBus.fireEvent(new BokSelectedEvent(call));
		    }
		});
	    }
	});

    }

    @Override
    public Path getPath() {
	return Paths.resource(callsResource);
    }

    @Override
    public void open(final Place place) {
	if (session.isCurrent(Bok.CALL, place.id)) {
	    eventBus.fireEvent(new BokSelectedEvent(session.getProjectBok(Bok.CALL)));
	} else {
	    calls.open(place.id, null, new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    eventBus.fireEvent(new BokSelectedEvent(event.getBok()));
		}
	    });
	}
    }

}
