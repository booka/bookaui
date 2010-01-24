package net.boklab.tools.client.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DefaultDispatcher implements Dispatcher {
    private final ActionsRegistry registry;
    private final ExecutionContext context;

    @Inject
    public DefaultDispatcher(ActionsRegistry registry) {
	this.registry = registry;
	this.context = new DefaultContext(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Action, C extends ActionCallback<R>, R> void execute(A action, C callback) {
	Provider<? extends ActionHandler<A, C>> provider = (Provider<? extends ActionHandler<A, C>>) registry
		.get(action.getClass());
	ActionHandler<A, C> actionHandler = provider.get();
	actionHandler.execute(action, callback, context);
    }
}
