package net.boklab.document.client;

import net.boklab.core.client.Bok;
import net.boklab.core.client.DelegatedBok;

public class Document extends DelegatedBok {
    public static final String TYPE = "Document";

    public Document(Bok delegate) {
	super(delegate, TYPE);
    }

}
