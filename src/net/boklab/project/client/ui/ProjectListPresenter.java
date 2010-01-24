package net.boklab.project.client.ui;

import net.boklab.tools.client.dispatcher.Dispatcher;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceChangedHandler;
import net.boklab.tools.client.place.Router;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class ProjectListPresenter extends AbstractPresenter<ProjectListDisplay> {

    @Inject
    public ProjectListPresenter(EventBus eventBus, Dispatcher dispatcher, ProjectListDisplay display) {
	super(display);

	eventBus.addHandler(PlaceChangedEvent.TYPE, new PlaceChangedHandler() {
	    @Override
	    public void onPlaceChanged(PlaceChangedEvent event) {
		if (Router.is(event.getPlace(), "archives")) {
		    Window.alert("JODER!");
		}
	    }
	});
    }

}
