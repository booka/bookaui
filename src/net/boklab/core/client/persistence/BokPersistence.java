package net.boklab.core.client.persistence;

import net.boklab.core.client.BokToParams;
import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.CreateBokEvent;
import net.boklab.core.client.bok.events.CreateBokHandler;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.core.client.bok.events.RetrieveBokHandler;
import net.boklab.core.client.bok.events.UpdateBokEvent;
import net.boklab.core.client.bok.events.UpdateBokHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponseJSO;
import net.boklab.core.client.model.DelegatedBok;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BokPersistence {
    protected static final String RESOURCE = "boks";
    private final EventBus eventBus;
    private final RestManager manager;

    @Inject
    public BokPersistence(final EventBus eventBus, final RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;

	eventBus.addHandler(RetrieveBokEvent.TYPE, new RetrieveBokHandler() {
	    @Override
	    public void onGetBok(final RetrieveBokEvent event) {
		retrieve(event.getBokId(), event.getHandler());
	    }
	});

	eventBus.addHandler(CreateBokEvent.TYPE, new CreateBokHandler() {
	    @Override
	    public void onCreateBok(final CreateBokEvent event) {
		create(event.getBok(), event.getHandler());
	    }
	});

	eventBus.addHandler(UpdateBokEvent.getType(), new UpdateBokHandler() {
	    @Override
	    public void onUpdateBok(final UpdateBokEvent event) {
		update(event.getBok());
	    }
	});
    }

    private void fireUpdatedBoks(final BokResponseJSO response) {
	final int total = response.getUpdatedSize();
	for (int index = 0; index < total; index++) {
	    final Bok bok = new DelegatedBok(response.getUpdated(index));
	    eventBus.fireEvent(new BokUpdatedEvent(bok));
	}
    }

    protected void create(final Bok bok, final BokCreatedHandler handler) {
	final Params params = BokToParams.encode(bok, new Params());
	manager.create("boks.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokResponseJSO response = JsonUtils.unsafeEval(text);
		final Bok createdBok = DelegatedBok.build(response);
		final BokCreatedEvent event = new BokCreatedEvent(createdBok);
		if (handler != null) {
		    handler.onBokCreated(event);
		}
		eventBus.fireEvent(event);
		fireUpdatedBoks(response);
	    }
	});
    }

    protected void retrieve(final String bokId, final BokRetrievedHandler handler) {
	manager.get("boks.get", RESOURCE, bokId, new RestCallback() {
	    @Override
	    public void onSuccess(final String content) {
		final BokResponseJSO response = JsonUtils.unsafeEval(content);
		final Bok retrievedBok = DelegatedBok.build(response);
		final BokRetrievedEvent event = new BokRetrievedEvent(retrievedBok);
		if (handler != null) {
		    handler.onBokRetrieved(event);
		}
		eventBus.fireEvent(event);
	    }
	});

    }

    protected void update(final Bok bok) {
	final Params params = BokToParams.encode(bok, new Params());
	manager.update("clips.update", RESOURCE, bok.getId(), params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokResponseJSO response = JsonUtils.unsafeEval(text);
		final Bok updatedBok = DelegatedBok.build(response);
		eventBus.fireEvent(new BokUpdatedEvent(updatedBok));
		fireUpdatedBoks(response);
	    }
	});
    }

}
