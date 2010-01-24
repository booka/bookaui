package net.boklab.project.client.model;

import net.boklab.core.client.Bok;
import net.boklab.core.client.BokJSO;
import net.boklab.core.client.DelegatedBok;
import net.boklab.document.client.Document;

public class Project extends DelegatedBok {
    public static final String TYPE = "Project";

    public Project(Bok bok) {
	super(bok, TYPE);
    }

    @Override
    public String getBokType() {
	return TYPE;
    }

    public Document newDocument(String title) {
	Document document = new Document(BokJSO.newInstance(Document.TYPE));
	document.setTitle(title);
	document.setParentId(this.getId());
	return document;
    }
}
