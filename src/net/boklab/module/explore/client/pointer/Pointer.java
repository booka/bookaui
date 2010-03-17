package net.boklab.module.explore.client.pointer;

public class Pointer implements Comparable<Pointer> {

    private String position;
    private String title;
    private final String documentId;

    public Pointer() {
	this("", "", "");
    }

    public Pointer(final String position, final String title, final String documentId) {
	setPosition(position);
	setTitle(title);
	this.documentId = documentId;
    }

    @Override
    public int compareTo(final Pointer o) {
	return position.compareTo(o.position);
    }

    public String getDocumentId() {
	return documentId;
    }

    public String getPosition() {
	return position;
    }

    public String getTitle() {
	return title;
    }

    public void setPosition(final String position) {
	this.position = position;
    }

    public void setTitle(final String title) {
	this.title = title;
    }

}
