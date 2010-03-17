package net.boklab.module.entrance.client.project.browser;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.BokSelectedEvent;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private Bok project;
    private final EventBus eventBus;

    @Inject
    public ProjectPresenter(final EventBus eventBus, final Provider<ProjectDisplay> display) {
	super(display);
	this.eventBus = eventBus;

    }

    public void setProject(final Bok project) {
	this.project = project;
	getDisplay().getTitleHeader().setText(project.getTitle());

    }

    @Override
    protected void attach(final ProjectDisplay display) {
	display.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		// projects.open(project.getId(), project.getTitle(), false);
		eventBus.fireEvent(new BokSelectedEvent(project));
	    }
	});
    }

}
