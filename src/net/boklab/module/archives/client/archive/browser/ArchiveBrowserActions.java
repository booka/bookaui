package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.ui.browser.BrowserActions;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchiveBrowserActions extends
	BrowserActions<ArchiveBrowserPresenter, ArchiveItemPresenter> {

    @Inject
    public ArchiveBrowserActions() {
    }
}
