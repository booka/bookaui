package net.boklab.archives.client;

import net.boklab.browser.client.BrowserGinjector;

import com.google.gwt.inject.client.GinModules;

@GinModules(ArchivesModule.class)
public interface ArchivesGinjector extends BrowserGinjector {
    ArchivesPresenter getArchivesPresenter();
}
