package net.boklab.module.archives.client.archive.browser.actions;

import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.module.archives.client.I18nArchives;
import net.boklab.module.archives.client.archive.browser.ArchiveBrowserAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EditDocumentAction extends ArchiveBrowserAction {

    @Inject
    public EditDocumentAction(final UserSessionManager sessions) {
	super(I18nArchives.t.actionNewDocument(), Icons.get(BokIcon.edit), sessions);
	setVisibility(VisibilityPolicy.selected);
    }

    @Override
    public void execute() {
    }

}
