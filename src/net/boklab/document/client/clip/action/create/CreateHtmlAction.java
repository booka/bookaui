package net.boklab.document.client.clip.action.create;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.content.html.HtmlContentType;
import net.boklab.document.client.content.slot.SlotContentType;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CreateHtmlAction extends ClipAction {

    public static final String TYPE = "CreateHtml";
    private final ContentTypeManager manager;

    @Inject
    public CreateHtmlAction(final ContentTypeManager manager) {
	super(TYPE, "New Text");
	this.manager = manager;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Clip clip = presenter.getClip();
	clip.setContentType(HtmlContentType.TYPE);
	final ClipEditorDisplay editor = manager.newEditor(clip);
	presenter.setEditor(editor);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return presenter.isClipType(SlotContentType.TYPE);
    }

}
