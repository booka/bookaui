package net.boklab.module.archives.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.archives.client.archive.ArchiveManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchivesNavigator implements NavigationHandler {

    private final ClientSession session;
    private final ArchiveManager archives;

    @Inject
    public ArchivesNavigator(final ClientSession session, final ArchiveManager archives) {
	this.session = session;
	this.archives = archives;
    }

    @Override
    public void onNavigation(final NavigationEvent event) {
	if (session.has(Bok.DOCUMENT)) {
	    session.select(Bok.DOCUMENT);
	} else if (session.has(Bok.ARCHIVE)) {
	    session.select(Bok.ARCHIVE);
	} else if (session.getProject() != null) {
	    archives.openArchivesOfProject(session.getProject(), new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    session.select(event.getBok());
		}
	    });
	} else {
	    session.select(session.getSite());
	}
    }

}
