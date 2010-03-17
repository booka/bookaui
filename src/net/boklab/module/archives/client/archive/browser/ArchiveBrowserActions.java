package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.ui.browser.BrowserActions;
import net.boklab.module.archives.client.archive.browser.actions.DeleteDocumentAction;
import net.boklab.module.archives.client.archive.browser.actions.EditDocumentAction;
import net.boklab.module.archives.client.archive.browser.actions.NewDocumentAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchiveBrowserActions extends
	BrowserActions<ArchiveBrowserPresenter, ArchiveItemPresenter> {

    @Inject
    public ArchiveBrowserActions(final NewDocumentAction newDocument,
	    final EditDocumentAction editDocument, final DeleteDocumentAction deleteDocument) {
	addAction(newDocument);
	addAction(editDocument);
	addAction(deleteDocument);
    }
}
