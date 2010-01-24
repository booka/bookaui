package net.boklab.archives.client;

import net.boklab.browser.client.BrowserGinjector;

public interface ArchivesGinjector extends BrowserGinjector {
    ArchivesPresenter getArchivesPresenter();
}
