package net.boklab.module.explore.client;

import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.core.client.ui.browser.BrowserWidget;
import net.boklab.module.explore.client.browser.IndiceBrowserActionRegistrar;
import net.boklab.module.explore.client.editor.PointerEditorDisplay;
import net.boklab.module.explore.client.editor.PointerEditorWidget;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.module.explore.client.pointer.PointerDisplay;
import net.boklab.module.explore.client.pointer.PointerWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ExploreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(IndiceManager.class).asEagerSingleton();
	bind(IndiceBrowserActionRegistrar.class).asEagerSingleton();
	bind(BrowserDisplay.class).to(BrowserWidget.class);
	bind(PointerDisplay.class).to(PointerWidget.class);
	bind(PointerEditorDisplay.class).to(PointerEditorWidget.class);
    }
}
