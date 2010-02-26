package net.boklab.document.client.actions;

import net.boklab.core.client.I18nBok;
import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.clip.action.ClipActionsPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ActionsRegistrator {

    private static final String TYPE = "CloseAction";

    @Inject
    public ActionsRegistrator(final ClipActionsPresenter registry, final LoginToEditAction loginToEditAction,
	    final EditClipAction editAction, final CreateHtmlAction createHtml, final DebugAction debug) {

	registry.add(loginToEditAction);
	registry.add(editAction);
	registry.add(createHtml);
	registry.add(debug);

	registry.add(new ClipAction(TYPE, I18nBok.t.closeAction()) {
	    @Override
	    public void execute(final ClipPresenter presenter) {
		presenter.setEditor(null);
	    }

	    @Override
	    public boolean isApplicable(final ClipPresenter presenter) {
		return true;
	    }
	});
    }
}
