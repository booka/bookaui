package net.boklab.project.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.BokResponse;
import net.boklab.core.client.model.DelegatedBok;
import net.boklab.document.client.model.Document;

public class Project extends DelegatedBok implements Iterable<Document> {
    public static final String TYPE = "Project";
    private List<Document> list;

    public Project(Bok bok) {
	this(bok, null);
    }

    public Project(Bok bok, BokResponse results) {
	super(bok, TYPE);
	setResults(results);
    }

    @Override
    public String getBokType() {
	return TYPE;
    }

    @Override
    public Iterator<Document> iterator() {
	return list.iterator();
    }

    public Document newDocument(String title) {
	Document document = new Document(BokJSO.newInstance(Document.TYPE));
	document.setTitle(title);
	document.setParentId(this.getId());
	return document;
    }

    private void setResults(BokResponse results) {
	if (results != null) {
	    this.list = new ArrayList<Document>();
	    int total = results.getChildrenSize();
	    for (int index = 0; index < total; index++) {
		list.add(new Document(results.getChildren(index)));
	    }
	} else {
	    this.list = Collections.EMPTY_LIST;
	}
    }
}
