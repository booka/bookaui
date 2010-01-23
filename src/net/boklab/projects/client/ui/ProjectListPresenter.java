package net.boklab.projects.client.ui;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;

public class ProjectListPresenter extends AbstractPresenter<ProjectListDisplay> {

    @Inject
    public ProjectListPresenter(EventBus eventBus, ProjectListDisplay display) {
	super(display);
    }

}
