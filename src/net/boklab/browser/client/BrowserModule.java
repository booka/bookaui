package net.boklab.browser.client;

import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.browser.client.ui.DocumentBrowserWidget;
import net.boklab.browser.client.ui.DocumentItemDisplay;
import net.boklab.browser.client.ui.DocumentItemWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BrowserModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(DocumentBrowserDisplay.class).to(DocumentBrowserWidget.class);
	bind(DocumentItemDisplay.class).to(DocumentItemWidget.class);
    }

}
