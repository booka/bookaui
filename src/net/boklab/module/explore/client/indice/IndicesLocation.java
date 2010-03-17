package net.boklab.module.explore.client.indice;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.module.Location;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.BokSelectedHandler;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.entrance.client.I18nEntrance;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndicesLocation implements Location {

    private final String indicesResource;
    private final ClientSession session;
    private final EventBus eventBus;
    private final IndiceManager indices;

    @Inject
    public IndicesLocation(final EventBus eventBus, final Router router,
	    final NavigationPresenter navigation, final ClientSession session,
	    final IndiceManager indices, final IndicesWorkspace workspace) {
	this.eventBus = eventBus;
	this.session = session;
	this.indices = indices;
	indicesResource = I18nEntrance.t.resourceBrowse();

	eventBus.addHandler(BokSelectedEvent.getType(), new BokSelectedHandler() {
	    @Override
	    public void onBokSelected(final BokSelectedEvent event) {
		final Bok bok = event.getBok();
		if (Bok.INDICE.equals(bok.getBokType())) {
		    onIndiceSelected(bok);
		}
	    }

	    private void onIndiceSelected(final Bok bok) {
		router.setCurrent(new Place(indicesResource, bok.getId()));
		navigation.setActiveIcon(NavigationDisplay.BOOKA);
		workspace.show();
		navigation.setCurrentLocation(I18nEntrance.t.locationIndice(bok.getTitle()));
	    }
	});
    }

    @Override
    public Path getPath() {
	return Paths.resource(indicesResource);
    }

    @Override
    public void open(final Place place) {
	if (session.isCurrent(Bok.INDICE, place.id)) {
	    eventBus.fireEvent(new BokSelectedEvent(session.getProjectBok(Bok.INDICE)));
	} else {
	    indices.open(place.id, null, new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    eventBus.fireEvent(new BokSelectedEvent(event.getBok()));
		}
	    });
	}
    }

}
