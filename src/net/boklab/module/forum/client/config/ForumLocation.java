package net.boklab.module.forum.client.config;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.BokSelectedHandler;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.forum.client.I18nForum;
import net.boklab.module.forum.client.manager.ForumManager;
import net.boklab.places.client.Location;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumLocation implements Location {
    private final String forumResources;
    private final ClientSession session;
    private final ForumManager forums;

    @Inject
    public ForumLocation(final ClientSession session, final Router router,
	    final ForumManager forums, final ForumWorkspace workspace) {
	this.session = session;
	this.forums = forums;
	forumResources = I18nForum.t.resourceForum();

	session.addBokSelectedHandler(Bok.FORUM, new BokSelectedHandler() {
	    @Override
	    public void onBokSelected(final BokSelectedEvent event) {
		final Bok forum = event.getBok();
		router.setCurrent(new Place(forumResources, forum.getId()));
		workspace.show(I18nForum.t.locationForum(forum.getTitle()));
	    }
	});
    }

    @Override
    public Path getPath() {
	return Paths.resource(forumResources);
    }

    @Override
    public void open(final Place place) {
	if (session.isCurrent(Bok.FORUM, place.id)) {
	    session.select(Bok.FORUM);
	} else {
	    forums.open(place.id, null, new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    session.select(event.getBok());
		}
	    });
	}
    }

}
