package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;

public abstract class ContentHandler {

    private final String type;

    public ContentHandler(final String mimeType) {
	type = mimeType;
    }

    public String getType() {
	return type;
    }

    public abstract ContentEditor<?> newClipEditor(final Bok bok);

    public abstract String render(final Bok bok);

}
