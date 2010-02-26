package net.boklab.document.client.content.slot;

import net.boklab.document.client.content.ClipContentType;
import net.boklab.document.client.content.ContentTypeEditorPresenter;
import net.boklab.document.client.model.Clip;

public class SlotContentType extends ClipContentType {
    public static final String TYPE = "clip/slot";

    public SlotContentType() {
	super("slot", TYPE);
    }

    @Override
    public ContentTypeEditorPresenter<?> newClipEditor(final Clip clip) {
	return null;
    }

    @Override
    public String render(final Clip clip) {
	return "<div class=\"bk-Slot\" />";
    }

}
