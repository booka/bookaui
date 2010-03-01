package net.boklab.document.client.actions;

import net.boklab.core.client.I18nBok;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.action.BokActionsPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ActionsRegistrator {

    private static final String TYPE = "CloseAction";

    @Inject
    public ActionsRegistrator(final BokActionsPresenter registry, final LoginToEditAction loginToEditAction,
	    final EditClipAction editAction, final CreateHtmlAction createHtml, final DebugAction debug) {

	registry.add(loginToEditAction);
	registry.add(editAction);
	registry.add(createHtml);
	registry.add(debug);

	registry.add(new BokAction(TYPE, I18nBok.t.closeAction()) {
	    @Override
	    public void execute(final BokPresenter presenter) {
		presenter.setEditor(null);
		if (presenter.getBok() == null) {
		    presenter.destroy();
		}
	    }

	    @Override
	    public boolean isApplicable(final BokPresenter presenter) {
		return true;
	    }
	});
    }
}
