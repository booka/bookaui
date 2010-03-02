package net.boklab.document.client.content;

import net.boklab.document.client.content.debug.DebugContentHandler;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.content.info.DocumentContentHandler;
import net.boklab.document.client.content.link.LinkContentHandler;

import com.google.inject.Inject;

public class ContentRegistrator {

    @Inject
    public ContentRegistrator(final ContentHandlerRegistry registry, final HtmlContentHandler htmlContentHandler,
	    final DebugContentHandler debugContentHandler, final DocumentContentHandler documentContentTypeHandler,
	    final LinkContentHandler linkContentHandler) {
	registry.register(htmlContentHandler);
	registry.register(linkContentHandler);
	registry.register(debugContentHandler);
	registry.register(documentContentTypeHandler);
    }
}
