package net.boklab.document.client.actions;

import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.content.slot.SlotContentHandler;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CreateHtmlAction extends ClipAction {

    public static final String TYPE = "CreateHtml";
    private final ContentTypeManager manager;
    private final Sessions sessions;

    @Inject
    public CreateHtmlAction(final ContentTypeManager manager, final Sessions sessions) {
	super(TYPE, "New Text");
	this.manager = manager;
	this.sessions = sessions;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Clip clip = presenter.getClip();
	clip.setContentType(HtmlContentHandler.TYPE);
	final ClipEditorDisplay editor = manager.newEditor(clip);
	presenter.setEditor(editor);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return sessions.isLoggedIn() && presenter.isClipType(SlotContentHandler.TYPE);
    }

}
