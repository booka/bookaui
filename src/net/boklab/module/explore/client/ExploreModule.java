package net.boklab.module.explore.client;

import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.core.client.ui.browser.BrowserWidget;
import net.boklab.module.explore.client.pointer.PointerDisplay;
import net.boklab.module.explore.client.pointer.PointerWidget;
import net.boklab.module.explore.client.pointer.editor.PointerEditorDisplay;
import net.boklab.module.explore.client.pointer.editor.PointerEditorWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ExploreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ExploreConfiguration.class).asEagerSingleton();
	bind(BrowserDisplay.class).to(BrowserWidget.class);
	bind(PointerDisplay.class).to(PointerWidget.class);
	bind(PointerEditorDisplay.class).to(PointerEditorWidget.class);
    }
}
