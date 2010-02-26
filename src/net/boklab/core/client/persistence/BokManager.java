package net.boklab.core.client.persistence;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokQuery;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BokManager {
    private final EventBus eventBus;

    @Inject
    public BokManager(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    public void create(final Bok bok, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(bok, handler));
    }

    public void list(final BokQuery query, final BokListRetrievedHandler handler) {
	eventBus.fireEvent(new RetrieveBokListEvent(query, handler));
    }

    public void retrieve(final String bokId, final BokRetrievedHandler handler) {
	eventBus.fireEvent(new RetrieveBokEvent(bokId, handler));
    }

    public void update(final Bok bok, final BokUpdatedHandler handler) {
	eventBus.fireEvent(new UpdateBokEvent(bok, handler));
    }

}
