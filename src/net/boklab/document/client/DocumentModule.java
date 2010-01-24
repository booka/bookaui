package net.boklab.document.client;

import net.boklab.document.client.manager.DefaultDocumentManager;
import net.boklab.document.client.manager.DocumentManager;

import com.google.gwt.inject.client.AbstractGinModule;

public class DocumentModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(DocumentManager.class).to(DefaultDocumentManager.class).asEagerSingleton();
    }

}
