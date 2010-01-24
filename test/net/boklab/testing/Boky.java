package net.boklab.testing;

import java.util.ArrayList;

import net.boklab.core.client.SimpleBok;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.model.DocumentClips;
import net.boklab.project.client.model.Project;

public class Boky {
    static final int SIZE = 10;
    private static int id = 0;

    public static Bok bok(String type) {
	newModel();
	SimpleBok bok = new SimpleBok(id(type), type);
	bok.setTitle(id("title"));
	bok.setDescription(id("description"));
	return bok;
    }

    public static Document document() {
	Document document = new Document(bok(Document.TYPE));
	return document;
    }

    public static DocumentClips emptyDocumentClips() {
	Document document = document();
	DocumentClips documentClips = new DocumentClips(document, null);
	return documentClips;
    }

    public static Project project() {
	return new Project(bok(Project.TYPE), null);
    }

    public static ArrayList<Project> projectList() {
	ArrayList<Project> list = new ArrayList<Project>();
	for (int index = 0; index < SIZE; index++) {
	    list.add(project());
	}
	return list;
    }

    private static String id(String model) {
	return model + "-" + id;
    }

    private static void newModel() {
	id++;
    }

}
