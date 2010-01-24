package net.boklab.document.client.model;

import java.util.ArrayList;
import java.util.Iterator;

import net.boklab.core.client.BokSearchResults;

/**
 * The clips of a document model
 * 
 */
public class DocumentClips implements Iterable<Clip> {

    private final Document document;
    private final ArrayList<Clip> list;

    /**
     * Create a new model
     * 
     * @param document
     *            the document, required
     * @param results
     */
    public DocumentClips(Document document, BokSearchResults results) {
	assert document != null : "Document is required in DocumentClips";

	this.document = document;
	this.list = new ArrayList<Clip>();
	if (results != null)
	    setResults(results);
    }

    public Document getDocument() {
	return document;
    }

    @Override
    public Iterator<Clip> iterator() {
	return list.iterator();
    }

    private void setResults(BokSearchResults results) {
	list.clear();
	int total = results.getChildrenSize();
	for (int index = 0; index < total; index++) {
	    list.add(new Clip(results.getChildren(index)));
	}
    }

}
