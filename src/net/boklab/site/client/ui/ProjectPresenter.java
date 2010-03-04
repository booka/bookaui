package net.boklab.site.client.ui;

import net.boklab.core.client.model.Bok;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private String projectId;
    private String title;

    @Inject
    public ProjectPresenter(final ProjectManager projects, final Provider<ProjectDisplay> display) {
	super(display);
	getDisplay().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		projects.openProject(projectId, title, true);
	    }
	});
    }

    public void setProject(final Bok project) {
	projectId = project.getId();
	title = project.getTitle();
	getDisplay().getTitleHeader().setText(project.getTitle());

    }

}
