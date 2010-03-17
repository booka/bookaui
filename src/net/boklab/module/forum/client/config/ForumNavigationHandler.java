package net.boklab.module.forum.client.config;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.navigation.AbstractNavigationHandler;
import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.forum.client.manager.ForumManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumNavigationHandler extends AbstractNavigationHandler {
    private final ForumManager forums;

    @Inject
    public ForumNavigationHandler(final EventBus eventBus, final ClientSession session,
	    final ForumManager forums) {
	super(eventBus, session);
	this.forums = forums;
    }

    @Override
    public void onNavigation(final NavigationEvent event) {
	Bok project;
	if (session.has(Bok.FORUM)) {
	    fireSelected(session.getProjectBok(Bok.FORUM));
	} else if ((project = session.getProject()) != null) {
	    forums.openChildOfProject(project, new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    fireSelected(event.getBok());
		}
	    });
	}
    }
}
