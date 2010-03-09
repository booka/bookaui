package net.boklab.indices.client;

import net.boklab.indices.client.browser.BrowserDisplay;
import net.boklab.indices.client.browser.BrowserWidget;
import net.boklab.indices.client.editor.PointerEditorDisplay;
import net.boklab.indices.client.editor.PointerEditorWidget;
import net.boklab.indices.client.pointer.PointerDisplay;
import net.boklab.indices.client.pointer.PointerWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokIndicesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(IndiceManager.class).asEagerSingleton();
	bind(IndicesActions.class).asEagerSingleton();
	bind(BrowserDisplay.class).to(BrowserWidget.class);
	bind(PointerDisplay.class).to(PointerWidget.class);
	bind(PointerEditorDisplay.class).to(PointerEditorWidget.class);
    }
}
