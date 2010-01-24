package net.boklab.document.client;

import net.boklab.document.client.manager.DocumentManager;

import com.google.gwt.inject.client.GinModules;

@GinModules(DocumentModule.class)
public interface DocumentGinjector {
    DocumentManager getDocumentManager();
}
