package net.boklab.document.client.manager;

import net.boklab.core.client.BokToParams;
import net.boklab.core.client.model.BokRequestResultsJSO;
import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipsPersistence extends Persistence<Clip> {

    @Inject
    public ClipsPersistence(final EventBus eventBus, final RestManager manager) {
	super(eventBus, manager, Clip.TYPE);
    }

    @Override
    protected void create(final Clip clip) {
	assert clip.getParentId() != null : "Clip MUST have parent id";

	final Params params = BokToParams.encode(clip, new Params());
	manager.create("document.clip.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String content) {
	    }
	});
    }

    @Override
    protected void get(final String bokId) {

    }

    @Override
    protected void update(final Clip clip) {
	final Params params = BokToParams.encode(clip, new Params());
	manager.update("clips.update", RESOURCE, clip.getId(), params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		final Clip clip = new Clip(results.getBok());
		eventBus.fireEvent(new BokUpdatedEvent(clip));
	    }
	});
    }
}
