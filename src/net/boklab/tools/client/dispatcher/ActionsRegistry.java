package net.boklab.tools.client.dispatcher;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ActionsRegistry {
    private final HashMap<Class<? extends Action>, Provider<? extends ActionHandler<? extends Action, ActionCallback<? extends Object>>>> map;

    public ActionsRegistry() {
	this.map = new HashMap<Class<? extends Action>, Provider<? extends ActionHandler<? extends Action, ActionCallback<? extends Object>>>>();
    }

    public @SuppressWarnings("unchecked")
    <A extends Action> Provider<? extends ActionHandler<A, ? extends Object>> get(Class<A> actionClass) {
	return (Provider<? extends ActionHandler<A, ActionCallback<? extends Object>>>) map.get(actionClass);
    }

    @SuppressWarnings("unchecked")
    public <A extends Action, H extends ActionHandler<A, C>, C extends ActionCallback<R>, R extends Object> void register(
	    Class<A> actionClass, Provider<? extends ActionHandler<A, C>> provider) {
	GWT.log("Action:" + actionClass.getName(), null);
	map.put(actionClass,
		(Provider<? extends ActionHandler<? extends Action, ActionCallback<? extends Object>>>) provider);
    }

}
