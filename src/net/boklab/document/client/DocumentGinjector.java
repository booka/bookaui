package net.boklab.document.client;

import net.boklab.document.client.manager.Documents;

import com.google.gwt.inject.client.GinModules;

@GinModules(DocumentModule.class)
public interface DocumentGinjector {
    Documents getDocuments();
}
