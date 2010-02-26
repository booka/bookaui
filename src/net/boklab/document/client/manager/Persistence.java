package net.boklab.document.client.manager;

import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestManager;

public abstract class Persistence<T extends Bok> {
    protected static final String RESOURCE = "boks";

    protected final EventBus eventBus;
    protected final RestManager manager;

    public Persistence(final EventBus eventBus, final RestManager manager, final String bokType) {
	this.eventBus = eventBus;
	this.manager = manager;

	eventBus.addHandler(GetBokEvent.TYPE, new GetBokHandler() {
	    @Override
	    public void onGetBok(final GetBokEvent event) {
		if (event.isBokType(bokType)) {
		    get(event.getBokId());
		}
	    }
	});

	eventBus.addHandler(CreateBokEvent.TYPE, new CreateBokHandler() {
	    @SuppressWarnings("unchecked")
	    @Override
	    public void onCreateBok(final CreateBokEvent event) {
		if (event.isBokType(bokType)) {
		    create((T) event.getBok());
		}
	    }
	});

	eventBus.addHandler(UpdateBokEvent.TYPE, new UpdateBokHandler() {
	    @SuppressWarnings("unchecked")
	    @Override
	    public void onUpdateBok(final UpdateBokEvent event) {
		if (event.isBokType(bokType)) {
		    update((T) event.getBok());
		}
	    }
	});
    }

    protected abstract void create(final T bok);

    protected abstract void get(final String bokId);

    protected abstract void update(final T bok);

}
