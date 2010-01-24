package net.boklab.browser.client.ui;

import net.boklab.document.client.manager.DocumentManager;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.action.ProjectDocumentsHandler;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.model.Project;
import net.boklab.project.client.model.ProjectDocuments;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentBrowserPresenter extends AbstractPresenter<DocumentBrowserDisplay> {

    private Project currentProject;

    @Inject
    public DocumentBrowserPresenter(final DocumentManager documents, final ProjectManager projects,
	    final Provider<DocumentBrowserDisplay> displayProvider,
	    final Provider<DocumentItemPresenter> itemProvider) {
	super(displayProvider);

	this.currentProject = null;

	projects.onProjectDocuments(new ProjectDocumentsHandler() {
	    @Override
	    public void onProjectDocuments(ProjectDocuments documents) {
		currentProject = documents.getProject();
		getDisplay().getList().clear();
		for (Document d : documents) {
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
		projects.getProjectDocuments(currentProject.getId());
	    }
	});
    }

}
