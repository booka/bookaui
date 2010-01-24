package net.boklab.project.client.model;

import java.util.ArrayList;
import java.util.Iterator;

import net.boklab.core.client.BokSearchResults;
import net.boklab.document.client.model.Document;

public class ProjectDocuments implements Iterable<Document> {

    private final Project project;
    private final ArrayList<Document> list;

    public ProjectDocuments(Project project, BokSearchResults results) {
	this.project = project;
	this.list = new ArrayList<Document>();
	setResults(results);
    }

    public Project getProject() {
	return project;
    }

    @Override
    public Iterator<Document> iterator() {
	return list.iterator();
    }

    private void setResults(BokSearchResults results) {
	list.clear();
	int total = results.getSize();
	for (int index = 0; index < total; index++) {
	    list.add(new Document(results.get(index)));
	}
    }
}
