package net.boklab.indices.client;

import net.boklab.indices.client.browser.BrowserWidget;
import net.boklab.indices.client.browser.BrowserDisplay;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokIndicesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BrowserDisplay.class).to(BrowserWidget.class);
    }

}
