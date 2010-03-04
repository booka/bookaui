package net.boklab.core.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.eventbus.EventBus;

public abstract class AbstractBokManager implements BokManager {

    protected final EventBus eventBus;
    private final String bokType;
    private final Bok active;

    public AbstractBokManager(final String bokType, final EventBus eventBus, final PlainBokManager manager) {
	this.eventBus = eventBus;
	this.bokType = bokType;
	active = null;

	addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		manager.retrieve(event.getBokId(), null);
	    }
	});
    }

    @Override
    public void addCreatedHandler(final BokCreatedHandler handler) {
	eventBus.addHandler(BokCreatedEvent.getType(), new BokCreatedHandler() {
	    @Override
	    public void onBokCreated(final BokCreatedEvent event) {
		if (event.isBokType(bokType)) {
		    handler.onBokCreated(event);
		}
	    }
	});
    }

    @Override
    public void addOpenedHandler(final BokOpenedHandler handler) {
	eventBus.addHandler(BokOpenedEvent.getType(), new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		if (event.isBokType(bokType)) {
		    handler.onBokOpened(event);
		}
	    }
	});
    }

    @Override
    public void addOpenHandler(final OpenBokHandler handler) {
	eventBus.addHandler(OpenBokEvent.getType(), new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		if (event.isBokType(bokType)) {
		    handler.onOpenBok(event);
		}
	    }
	});
    }

    @Override
    public void addRetrievedHandler(final BokRetrievedHandler handler) {
	eventBus.addHandler(BokRetrievedEvent.getType(), new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		if (event.isBokType(bokType)) {
		    handler.onBokRetrieved(event);
		}
	    }
	});
    }

    @Override
    public void addUpdatedHandler(final BokUpdatedHandler handler) {
	eventBus.addHandler(BokUpdatedEvent.getType(), new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		if (event.isBokType(bokType)) {
		    handler.onBokUpdated(event);
		}
	    }
	});
    }

    public Bok getActive() {
	return active;
    }

    public boolean hasActive() {
	return active != null;
    }

    @Override
    public void open(final Bok bok, final boolean forceOpen) {
	eventBus.fireEvent(new BokOpenedEvent(bok, forceOpen));
    }

    @Override
    public void open(final String bokId, final String knownTitle, final boolean forceOpen) {
	eventBus.fireEvent(new OpenBokEvent(bokType, bokId, forceOpen, knownTitle));
    }
}
