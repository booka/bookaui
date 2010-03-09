package net.boklab.core.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.bok.events.CreateBokEvent;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.core.client.bok.events.UpdateBokEvent;
import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.msg.CreateMessageEvent.Level;

import com.google.gwt.core.client.GWT;

public abstract class AbstractBokManager implements BokManager {

    protected final EventBus eventBus;
    private final String bokType;
    private Bok active;
    private int opening;
    private final MessageManager messages;
    private final ManagerMessages i18n;
    private int updating;

    public AbstractBokManager(final String bokType, final EventBus eventBus,
	    final MessageManager messages, final ManagerMessages i18n) {
	this.eventBus = eventBus;
	this.bokType = bokType;
	this.messages = messages;
	this.i18n = i18n;
	opening = updating = MessageManager.NONE;
	setActive(null);

	addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent openEvent) {
		onOpen(openEvent);
	    }

	});

	addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		onOpened(event);
	    }
	});

	addUpdatedHandler(new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		onUpdated(event);
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

    @Override
    public void create(final Bok bok, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(bok, handler));
    }

    public Bok getActive() {
	return active;
    }

    public String getActiveId() {
	return active != null ? active.getId() : null;
    }

    public boolean hasActive() {
	return active != null;
    }

    public boolean isActive(final String id) {
	return active != null && active.getId().equals(id);
    }

    @Override
    public void open(final Bok bok, final boolean forceOpen) {
	if (MessageManager.isNot(opening)) {
	    opening = messages.createMessage(i18n.open(bok.getTitle()), Level.working);
	    eventBus.fireEvent(new BokOpenedEvent(bok, forceOpen));
	}
    }

    @Override
    public void open(final String bokId, final String knownTitle, final boolean forceOpen) {
	if (MessageManager.isNot(opening)) {
	    final String msg = knownTitle != null ? i18n.open(knownTitle) : i18n.open();
	    opening = messages.createMessage(msg, Level.working);
	    eventBus.fireEvent(new OpenBokEvent(bokType, bokId, forceOpen, knownTitle));
	}
    }

    public void setActive(final Bok active) {
	GWT.log("SET ACTIVE " + bokType + ": " + (active != null ? active.getId() : "none"));
	this.active = active;
    }

    public void update(final Bok bok, final BokUpdatedHandler handler) {
	if (MessageManager.isNot(updating)) {
	    updating = messages.createMessage(i18n.update(bok.getTitle()), Level.working);
	    eventBus.fireEvent(new UpdateBokEvent(bok, handler));
	}
    }

    protected void onOpen(final OpenBokEvent openEvent) {
	if (getActive() != null && getActive().getId().equals(openEvent.getBokId())) {
	    eventBus.fireEvent(new BokOpenedEvent(getActive(), openEvent.isChangePlace()));
	} else {
	    eventBus.fireEvent(new RetrieveBokEvent(openEvent.getBokId(),
		    new BokRetrievedHandler() {
			@Override
			public void onBokRetrieved(final BokRetrievedEvent retrievedEvent) {
			    setActive(retrievedEvent.getBok());
			    eventBus.fireEvent(new BokOpenedEvent(getActive(), false));
			}
		    }));
	}
    }

    protected void onOpened(final BokOpenedEvent event) {
	opening = messages.removeMessage(opening);
    }

    protected void onUpdated(final BokUpdatedEvent event) {
	updating = messages.removeMessage(updating);
    }
}
