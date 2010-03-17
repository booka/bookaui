package net.boklab.module.explore.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.explore.client.manager.IndiceManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ExplorerNavigator implements NavigationHandler {

    private final ClientSession session;
    private final IndiceManager indices;

    @Inject
    public ExplorerNavigator(final ClientSession session, final IndiceManager indices) {
	this.session = session;
	this.indices = indices;
    }

    @Override
    public void onNavigation(final NavigationEvent event) {
	if (session.has(Bok.INDICE)) {
	    session.select(Bok.INDICE);
	} else if (session.getProject() != null) {
	    indices.openIndiceOfProject(session.getProject(), new BokOpenedHandler() {
		@Override
		public void onBokOpened(final BokOpenedEvent event) {
		    session.select(event.getBok());
		}
	    });
	}
    }

}
