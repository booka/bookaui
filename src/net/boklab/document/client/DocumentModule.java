package net.boklab.document.client;

import net.boklab.document.client.manager.DefaultDocumentManager;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.document.client.ui.DocumentDisplay;
import net.boklab.document.client.ui.DocumentWidget;
import net.boklab.document.client.ui.clip.ClipDisplay;
import net.boklab.document.client.ui.clip.ClipWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DocumentModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(DocumentManager.class).to(DefaultDocumentManager.class).asEagerSingleton();
	bind(DocumentRouter.class).asEagerSingleton();
	bind(DocumentDisplay.class).to(DocumentWidget.class).in(Singleton.class);
	bind(ClipDisplay.class).to(ClipWidget.class);
    }

}
