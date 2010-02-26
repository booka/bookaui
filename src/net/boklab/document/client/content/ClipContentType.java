package net.boklab.document.client.content;

import net.boklab.document.client.model.Clip;

public abstract class ClipContentType {

    private final String label;
    private final String type;

    public ClipContentType(final String label, final String mimeType) {
	this.label = label;
	type = mimeType;
    }

    public String getLabel() {
	return label;
    }

    public String getType() {
	return type;
    }

    public abstract ContentTypeEditorPresenter<?> newClipEditor(final Clip clip);

    public abstract String render(final Clip clip);

}
