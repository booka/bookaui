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

    public Document(Bok delegate) {
	this(delegate, null);
    }

    public Document(Bok delegate, BokSearchResults results) {
	super(delegate, TYPE);
	setResults(results);
    }

    @Override
    public Iterator<Clip> iterator() {
	return list.iterator();
    }

    private void setResults(BokSearchResults results) {
	if (results != null) {
	    this.list = new ArrayList<Clip>();
	    int total = results.getChildrenSize();
	    for (int index = 0; index < total; index++) {
		list.add(new Clip(results.getChildren(index)));
	    }
	} else {
	    this.list = Collections.emptyList();
	}
    }
}
