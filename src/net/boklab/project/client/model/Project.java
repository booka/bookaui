package net.boklab.project.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.DelegatedBok;
import net.boklab.document.client.model.Document;

public class Project extends DelegatedBok {
    public static final String TYPE = "Project";
    private final List<Document> documents;

    @SuppressWarnings("unchecked")
    public Project(final Bok bok) {
	this(bok, Collections.EMPTY_LIST);
    }

    public Project(final Bok bok, final List<Bok> children) {
	super(bok, TYPE);
	documents = new ArrayList<Document>();
	for (final Bok child : children) {
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

    public Document newDocument(final String title) {
	final Document document = new Document(BokJSO.newInstance(Document.TYPE));
	document.setTitle(title);
	document.setParentId(getId());
	return document;
    }
}
