package net.boklab.document.client;

import net.boklab.document.client.content.ContentTypeRegistry;
import net.boklab.document.client.content.debug.DebugContentHandler;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.content.slot.SlotContentHandler;

import com.google.inject.Inject;

public class ContentTypeRegistrator {

    @Inject
    public ContentTypeRegistrator(final ContentTypeRegistry registry, final HtmlContentHandler htmlContentHandler,
	    final SlotContentHandler slotContentHandler, final DebugContentHandler debugContentHandler) {
	registry.register(htmlContentHandler);
	registry.register(slotContentHandler);
	registry.register(debugContentHandler);
    }
}
