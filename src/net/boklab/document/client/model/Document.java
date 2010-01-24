package net.boklab.document.client.model;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.DelegatedBok;

public class Document extends DelegatedBok {
    public static final String TYPE = "Document";

    public Document(Bok delegate) {
	super(delegate, TYPE);
    }

}
