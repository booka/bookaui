package net.boklab.module.entrance.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.session.ClientSession;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EntranceNavigator implements NavigationHandler {

    private final ClientSession session;

    @Inject
    public EntranceNavigator(final ClientSession session) {
	this.session = session;
    }

    @Override
    public void onNavigation(final NavigationEvent event) {
	if (session.has(Bok.CALL)) {
	    session.select(Bok.CALL);
	} else if (session.getProject() != null) {
	    session.select(session.getProject());
	} else {
	    session.select(session.getSite());
	}
    }
}
