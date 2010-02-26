package net.boklab.document.client.content.slot;

import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.model.Clip;

public class SlotContentHandler extends ContentHandler {
    public static final String TYPE = "clip/slot";

    public SlotContentHandler() {
	super(TYPE);
    }

    @Override
    public ContentEditor<?> newClipEditor(final Clip clip) {
	return null;
    }

    @Override
    public String render(final Clip clip) {
	return "<div class=\"bk-Slot\" />";
    }

}
