package net.boklab.document.client;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.clip.action.ClipActionsPresenter;
import net.boklab.document.client.clip.action.create.CreateHtmlAction;
import net.boklab.document.client.clip.action.edit.ClipEditAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipActionRegistrator {

    private static final String TYPE = "CloseAction";

    @Inject
    public ClipActionRegistrator(final ClipActionsPresenter registry, final ClipEditAction editAction,
	    final CreateHtmlAction createHtml) {
	registry.add(editAction);
	registry.add(createHtml);

	registry.add(new ClipAction(TYPE, "Close") {
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
