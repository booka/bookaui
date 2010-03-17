package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.ClientSession;
import net.boklab.document.client.ArchiveManager;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NavigationPlaces {

    @Inject
    public NavigationPlaces(final EventBus eventBus, final ClientSession session,
	    final NavigationPresenter navigation, final ArchiveManager archives,
	    final IndiceManager indices) {

	navigation.addNavigationHandler(new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.isNavigation(NavigationDisplay.ENTRANCE)) {
		    handleEntrance();
		} else if (event.isNavigation(NavigationDisplay.ARCHIVES)) {
		    handleArchives();
		} else if (event.isNavigation(NavigationDisplay.BOOKA)) {
		    handleIndices();
		}
	    }

	    private void fireSelected(final Bok bok) {
		eventBus.fireEvent(new BokSelectedEvent(bok));
	    }

	    private void handleArchives() {
		if (session.has(Bok.DOCUMENT)) {
		    fireSelected(session.getProjectBok(Bok.DOCUMENT));
		} else if (session.has(Bok.ARCHIVE)) {
		    fireSelected(session.getProjectBok(Bok.ARCHIVE));
		} else if (session.getProject() != null) {
		    archives.openArchivesOfProject(session.getProject(), new BokOpenedHandler() {
			@Override
			public void onBokOpened(final BokOpenedEvent event) {
			    fireSelected(event.getBok());
			}
		    });
		} else {
		    fireSelected(session.getSite());
		}
	    }

	    private void handleEntrance() {
		if (session.has(Bok.CALL)) {
		    fireSelected(session.getProjectBok(Bok.CALL));
		} else if (session.getProject() != null) {
		    fireSelected(session.getProject());
		} else {
		    fireSelected(session.getSite());
		}
	    }

	    private void handleIndices() {
		if (session.has(Bok.INDICE)) {
		    fireSelected(session.getProjectBok(Bok.ARCHIVE));
		} else if (session.getProject() != null) {
		    indices.openIndiceOfProject(session.getProject(), new BokOpenedHandler() {
			@Override
			public void onBokOpened(final BokOpenedEvent event) {
			    fireSelected(event.getBok());
			}
		    });
		}
	    }
	});
    }
}
