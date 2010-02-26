package net.boklab.document.client.content;

import net.boklab.document.client.model.Clip;

public abstract class ContentHandler {

    private final String type;

    public ContentHandler(final String mimeType) {
	type = mimeType;
    }

    public String getType() {
	return type;
    }

    public abstract ContentEditor<?> newClipEditor(final Clip clip);

    public abstract String render(final Clip clip);

}
