package net.boklab.document.client.model;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.DelegatedBok;

public class Clip extends DelegatedBok {

    public static final String TYPE = "Clip";

    public Clip() {
	super(BokJSO.newInstance(TYPE));
    }

    public Clip(final Bok delegate) {
	super(delegate);
    }

    @Deprecated
    public Document getDocument() {
	return new Document(getParent());
    }

    public void setDocument(final Document document) {
	super.setParent(document);
    }

}
