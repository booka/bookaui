package net.boklab.document.client.model;

import java.util.ArrayList;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.DelegatedBok;

public class Document extends DelegatedBok {
    public static final String TYPE = "Document";
    private final ArrayList<Clip> clips;

    public Document(final Bok delegate) {
	super(delegate);
	clips = new ArrayList<Clip>();
	for (final Bok child : getChildren()) {
	    clips.add(new Clip(child));
	}
    }

    @Deprecated
    public List<Clip> getClips() {
	return clips;
    }
}
