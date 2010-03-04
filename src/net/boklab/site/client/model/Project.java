package net.boklab.site.client.model;

import java.util.ArrayList;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.DelegatedBok;
import net.boklab.document.client.model.Document;

public class Project extends DelegatedBok {
    public static final String TYPE = "Project";
    private final List<Document> documents;

    public Project(final Bok bok) {
	super(bok);
	documents = new ArrayList<Document>();
	for (final Bok child : bok.getChildren()) {
	    documents.add(new Document(child));
	}
    }

    @Override
    public String getBokType() {
	return TYPE;
    }

    public List<Document> getDocuments() {
	return documents;
    }

    @Deprecated
    public Document newDocument(final String title) {
	final DelegatedBok child = newChild("Document", title, null, 0);
	return new Document(child);
    }
}
