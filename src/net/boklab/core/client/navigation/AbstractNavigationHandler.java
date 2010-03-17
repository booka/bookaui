package net.boklab.core.client.navigation;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.core.client.session.ClientSession;
import net.boklab.tools.client.eventbus.EventBus;

public abstract class AbstractNavigationHandler implements NavigationHandler {

    protected final EventBus eventBus;
    protected final ClientSession session;

    public AbstractNavigationHandler(final EventBus eventBus, final ClientSession session) {
	this.eventBus = eventBus;
	this.session = session;
    }

    protected void fireSelected(final Bok bok) {
	eventBus.fireEvent(new BokSelectedEvent(bok));
    }
}
