package net.boklab.indices.client.model;

import java.util.ArrayList;
import java.util.Collections;

import net.boklab.core.client.model.Bok;

import com.google.gwt.core.client.GWT;

public class Indice {
    private final Bok bok;
    private final ArrayList<Pointer> pointers;

    public Indice(final Bok bok) {
	this.bok = bok;
	pointers = new ArrayList<Pointer>();
	parse(bok.getBody());
    }

    public void add(final Pointer pointer) {
	pointers.add(pointer);
	Collections.sort(pointers);
    }

    public Iterable<Pointer> getPointers() {
	return pointers;
    }

    public Bok updateBok() {
	String encoded = "";
	for (final Pointer p : pointers) {
	    encoded += p.getPosition() + " \n";
	    encoded += p.getTitle() + " \n";
	    encoded += p.getDocumentId() + " \n";
	}
	bok.setBody(encoded);
	return bok;
    }

    private void parse(final String body) {
	final String[] lines = body.split("\n");
	GWT.log("INDICE parse " + lines.length + " lines");
	for (int index = 2; index < lines.length; index += 3) {
	    pointers.add(new Pointer(lines[index - 2], lines[index - 1], lines[index]));
	}
	Collections.sort(pointers);
    }

}
