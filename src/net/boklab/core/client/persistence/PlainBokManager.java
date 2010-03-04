package net.boklab.core.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.bok.events.CreateBokEvent;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.core.client.bok.events.UpdateBokEvent;
import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PlainBokManager {
    private final EventBus eventBus;

    @Inject
    public PlainBokManager(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    public void create(final Bok bok, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(bok, handler));
    }

    public void retrieve(final String bokId, final BokRetrievedHandler handler) {
	eventBus.fireEvent(new RetrieveBokEvent(bokId, handler));
    }

    public void update(final Bok bok, final BokUpdatedHandler handler) {
	eventBus.fireEvent(new UpdateBokEvent(bok, handler));
    }

}
