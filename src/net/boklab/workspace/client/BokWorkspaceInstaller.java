package net.boklab.workspace.client;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceChangedHandler;
import net.boklab.workspace.client.event.UserMessageEvent;
import net.boklab.workspace.client.event.UserMessageHandler;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BokWorkspaceInstaller {

    @Inject
    public BokWorkspaceInstaller(final EventBus eventBus, final NavigationPresenter navigation) {

	eventBus.addHandler(UserMessageEvent.getType(), new UserMessageHandler() {
	    @Override
	    public void onUserMessage(final UserMessageEvent event) {
		navigation.setMessage(event.getMessage());
	    }
	});

	eventBus.addHandler(PlaceChangedEvent.getType(), new PlaceChangedHandler() {
	    @Override
	    public void onPlaceChanged(final PlaceChangedEvent event) {
		navigation.setPlace(event.getDescription());
	    }
	});

	eventBus.fireEvent(new UserMessageEvent("Bienvenid@ a B()()ka"));
    }
}
