package net.boklab.document.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.DelegatedBok;

public class Document extends DelegatedBok {
    public static final String TYPE = "Document";
    private final ArrayList<Clip> clips;

    @SuppressWarnings("unchecked")
    public Document(final Bok delegate) {
	this(delegate, Collections.EMPTY_LIST);
    }

    public Document(final Bok delegate, final List<Bok> children) {
	super(delegate, TYPE);
	clips = new ArrayList<Clip>();
	for (final Bok child : children) {
	    clips.add(new Clip(child));
	}
    }

    public List<Clip> getClips() {
	return clips;
    }
}
