package net.boklab.document.client.content;

import net.boklab.document.client.content.debug.DebugContentHandler;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.content.info.DocumentContentTypeHandler;

import com.google.inject.Inject;

public class ContentHandlerRegistrator {

    @Inject
    public ContentHandlerRegistrator(final ContentHandlerRegistry registry, final HtmlContentHandler htmlContentHandler,
	    final DebugContentHandler debugContentHandler, final DocumentContentTypeHandler documentContentTypeHandler) {
	registry.register(htmlContentHandler);
	registry.register(debugContentHandler);
	registry.register(documentContentTypeHandler);
    }
}
