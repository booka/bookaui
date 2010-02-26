package net.boklab.document.client;

import net.boklab.document.client.content.ContentTypeRegistry;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.manager.Documents;

import com.google.gwt.inject.client.GinModules;

@GinModules(DocumentsModule.class)
public interface DocumentsGinjector {
    ContentTypeRegistry getContentTypeRegistry();

    Documents getDocuments();

    HtmlContentHandler getHtmlContentType();
}
