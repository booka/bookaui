package net.boklab.module.archives.client.places;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.module.Location;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.BokSelectedHandler;
import net.boklab.module.archives.client.archive.ArchiveManager;
import net.boklab.module.entrance.client.I18nEntrance;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchivesLocation implements Location {
    private final ArchiveManager archives;
    private final EventBus eventBus;

    @Inject
    public ArchivesLocation(final EventBus eventBus, final Router router,
	    final Provider<ArchivesWorkspace> workspace, final NavigationPresenter navigation,
	    final ArchiveManager archives) {
	this.eventBus = eventBus;
	this.archives = archives;

	eventBus.addHandler(BokSelectedEvent.getType(), new BokSelectedHandler() {
	    @Override
	    public void onBokSelected(final BokSelectedEvent event) {
		if (event.getBok().getBokType().equals(Bok.ARCHIVE)) {
		    navigation.setActiveIcon(NavigationDisplay.ARCHIVES);
		    final Bok archivesBok = event.getBok();
		    final String location = I18nEntrance.t.locationArchives(archivesBok.getTitle());
		    navigation.setCurrentLocation(location);
		    workspace.get().show(false);
		    router.setCurrent(new Place(I18nEntrance.t.resourceArchives(), archivesBok
			    .getId()));
		}
	    }
	});

	archives.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
	    }
	});
    }

    @Override
    public Path getPath() {
	return Paths.resource(I18nEntrance.t.resourceArchives());
    }

    @Override
    public void open(final Place place) {
	archives.open(place.id, null, new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		eventBus.fireEvent(new BokSelectedEvent(event.getBok()));
	    }
	});
    }

}
