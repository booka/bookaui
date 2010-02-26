package net.boklab.document.client.model;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.DelegatedBok;

public class Clip extends DelegatedBok {

    public static final String TYPE = "Clip";
    private Document document;

    public Clip() {
	super(BokJSO.newInstance(TYPE), TYPE);
    }

    public Clip(final Bok delegate) {
	super(delegate, TYPE);
    }

    public Document getDocument() {
	return document;
    }

    public void setDocument(final Document document) {
	this.document = document;
    }

}
