package net.boklab.document.client;

import net.boklab.document.client.content.ContentHandlerRegistry;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.persistence.Documents;

import com.google.gwt.inject.client.GinModules;

@GinModules(DocumentsModule.class)
public interface DocumentsGinjector {
    ContentHandlerRegistry getContentTypeRegistry();

    Documents getDocuments();

    HtmlContentHandler getHtmlContentType();
}
