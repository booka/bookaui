package net.boklab.document.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class DocumentsEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	I18nDocs.set((DocumentMessages) GWT.create(DocumentMessages.class));
    }

}
