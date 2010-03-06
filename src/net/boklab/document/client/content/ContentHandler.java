package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;

public abstract class ContentHandler {

    protected final String type;

    public ContentHandler(final String mimeType) {
	type = mimeType;
    }

    public String getType() {
	return type;
    }

    public abstract ContentEditor<?> newClipEditor();

    public abstract String render(final Bok bok);

}
