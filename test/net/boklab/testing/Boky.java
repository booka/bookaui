package net.boklab.testing;

import java.util.ArrayList;

import net.boklab.core.client.SimpleBok;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.DelegatedBok;

public class Boky {
    static final int SIZE = 10;
    private static int id = 0;

    public static Bok bok(final String type) {
	newModel();
	final SimpleBok bok = new SimpleBok(seq(type), type);
	bok.setTitle(seq("title"));
	bok.setDescription(seq("description"));
	return new DelegatedBok(bok);
    }

    public static Bok document() {
	return bok("Document");
    }

    public static Bok project() {
	return bok("Project");
    }

    public static ArrayList<Bok> projectList() {
	final ArrayList<Bok> list = new ArrayList<Bok>();
	for (int index = 0; index < SIZE; index++) {
	    list.add(project());
	}
	return list;
    }

    private static void newModel() {
	id++;
    }

    private static String seq(final String model) {
	return model + "-" + id;
    }

    protected static ArrayList<Bok> results(final String type, final int size) {
	final ArrayList<Bok> children = new ArrayList<Bok>();
	for (int index = 0; index < size; index++) {
	    children.add(bok(type));
	}
	return children;
    }

}
