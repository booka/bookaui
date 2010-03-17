package net.boklab.module.archives.client.places;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.module.Location;
import net.boklab.core.client.session.ClientSession;
import net.boklab.document.client.DocumentManager;
import net.boklab.module.archives.client.archive.ArchiveManager;
import net.boklab.module.entrance.client.I18nEntrance;
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
public class DocumentLocation implements Location {

    private final DocumentManager documents;

    @Inject
    public DocumentLocation(final Router router, final NavigationPresenter navigation,
	    final ClientSession session, final DocumentManager documents,
	    final Provider<ArchivesWorkspace> workspace, final ArchiveManager archives) {
	this.documents = documents;

	final String documentsResource = I18nEntrance.t.resourceDocuments();

	documents.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok document = event.getBok();
		workspace.get().setDocument(document);
		navigation.setCurrentLocation(document.getTitle());
		navigation.setActiveIcon(NavigationDisplay.ARCHIVES);
		router.setCurrent(new Place(documentsResource, document.getId()));
		workspace.get().show(true);

		DeferredCommand.addCommand(new Command() {
		    @Override
		    public void execute() {
			final String archivesId = document.getParentId();
			if (!session.isCurrent(Bok.ARCHIVE, archivesId)) {
			    archives.open(archivesId, null, null);
			}
		    }
		});
	    }
	});
    }

    @Override
    public Path getPath() {
	final String documentsResource = I18nEntrance.t.resourceDocuments();
	return Paths.resource(documentsResource);
    }

    @Override
    public void open(final Place place) {
	documents.open(place.id, null, null);
    }

}
