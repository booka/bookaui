package net.boklab.document.client.actions;

import net.boklab.core.client.I18nCore;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.action.BokActionsPresenter;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.content.link.LinkContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ActionsRegistrator {

    private static final String TYPE = "CloseAction";

    @Inject
    public ActionsRegistrator(final BokActionsPresenter registry, final LoginToEditAction loginToEditAction,
	    final EditClipAction editAction, final Provider<CreateContentAction> createProvider, final DebugAction debug) {

	registry.add(loginToEditAction);
	registry.add(editAction);
	registry.add(createProvider.get().config(HtmlContentHandler.MIME, I18nDocs.t.createHtmlAction()));
	registry.add(createProvider.get().config(LinkContentHandler.MIME, I18nDocs.t.createLinkAction()));
	registry.add(debug);

	registry.add(new BokAction(TYPE, I18nCore.t.closeAction()) {
	    @Override
	    public void execute(final BokPresenter presenter) {
		presenter.setEditor(null);
		if (presenter.getContentHandler() == null) {
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
