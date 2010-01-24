package net.boklab.browser.client.ui;

import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentBrowserPresenter extends AbstractPresenter<DocumentBrowserDisplay> {

    private Project currentProject;

    @Inject
    public DocumentBrowserPresenter(final Documents documents, final Projects projects,
	    final Provider<DocumentBrowserDisplay> displayProvider,
	    final Provider<DocumentItemPresenter> itemProvider) {
	super(displayProvider);

	this.currentProject = null;

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(Project project) {
		currentProject = project;
		getDisplay().getList().clear();
		for (Document d : project) {
		    DocumentItemPresenter item = itemProvider.get();
		    item.setDocument(d);
		    getDisplay().getList().add(item.getDisplay().asWidget());
		}
		getDisplay().setCreateVisible(true);
	    }
	});

	getDisplay().getCreate().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		assert currentProject != null : "You should receive onNewDocument event without documents loaded previously";
		Document document = currentProject.newDocument("Sin título");
		documents.createDocument(document);
		projects.openProject(currentProject.getId());
	    }
	});
    }

}
