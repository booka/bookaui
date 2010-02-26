package net.boklab.core.client.persistence;

import net.boklab.core.client.BokToParams;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokQuery;
import net.boklab.core.client.model.BokResponseJSO;
import net.boklab.document.client.model.Clip;
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

	eventBus.addHandler(RetrieveBokListEvent.getType(), new RetrieveBokListHander() {
	    @Override
	    public void onRetrieveBokList(final RetrieveBokListEvent event) {
		getList(event.getQuery(), event.getHandler());
	    }
	});

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

    protected void create(final Bok bok, final BokCreatedHandler handler) {
	final Params params = BokToParams.encode(bok, new Params());
	manager.create("boks.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokResponseJSO response = JsonUtils.unsafeEval(text);
		final BokCreatedEvent event = new BokCreatedEvent(response);
		if (handler != null) {
		    handler.onBokCreated(event);
		}
		eventBus.fireEvent(event);
	    }
	});
    }

    protected void getList(final BokQuery query, final BokListRetrievedHandler handler) {

	manager.getList("boks.list", RESOURCE, query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokResponseJSO response = JsonUtils.<BokResponseJSO> unsafeEval(text);
		final BokListRetrievedEvent event = new BokListRetrievedEvent(response);
		if (handler != null) {
		    handler.onBokListRetrieved(event);
		}
		eventBus.fireEvent(event);
	    }
	});
    }

    protected void retrieve(final String bokId, final BokRetrievedHandler handler) {
	final BokQuery query = new BokQuery();
	query.bokParentEquals(bokId);
	query.bokTypeEquals(Clip.TYPE);
	final Params params = query.toParams();
	final String url = RESOURCE + "/" + bokId + "/children";

	manager.getList("boks.get", url, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokResponseJSO response = JsonUtils.unsafeEval(text);
		final BokRetrievedEvent event = new BokRetrievedEvent(response);
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
		eventBus.fireEvent(new BokUpdatedEvent(response));
	    }
	});
    }

}
