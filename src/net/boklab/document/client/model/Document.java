package net.boklab.document.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokSearchResults;
import net.boklab.core.client.model.DelegatedBok;

public class Document extends DelegatedBok implements Iterable<Clip> {
    public static final String TYPE = "Document";
    private List<Clip> list;

    public Document(final Bok delegate) {
	this(delegate, null);
    }

    public Document(final Bok delegate, final BokSearchResults results) {
	super(delegate, TYPE);
	setResults(results);
    }

    public List<Clip> getClips() {
	return list;
    }

    @Override
    public Iterator<Clip> iterator() {
	return list.iterator();
    }

    private void setResults(final BokSearchResults results) {
	if (results != null) {
	    list = new ArrayList<Clip>();
	    final int total = results.getChildrenSize();
	    for (int index = 0; index < total; index++) {
		final Clip clip = new Clip(results.getChildren(index));
		clip.setDocument(this);
		list.add(clip);
	    }
	} else {
	    list = Collections.emptyList();
	}
    }
}
