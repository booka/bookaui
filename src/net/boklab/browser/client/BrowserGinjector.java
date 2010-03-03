package net.boklab.browser.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.browser.client.ui.DocumentItemPresenter;
import net.boklab.site.client.SiteGinjector;

import com.google.gwt.inject.client.GinModules;

@GinModules(BrowserModule.class)
public interface BrowserGinjector extends SiteGinjector {
    DocumentBrowserPresenter getBrowserPresenter();

    DocumentItemPresenter getDocumentItemPresenter();
}
