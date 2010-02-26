package net.boklab.document.client;

import net.boklab.document.client.content.ContentTypeRegistry;
import net.boklab.document.client.content.html.HtmlContentType;
import net.boklab.document.client.content.slot.SlotContentType;

import com.google.inject.Inject;

public class ContentTypeRegistrator {

    @Inject
    public ContentTypeRegistrator(final ContentTypeRegistry registry, final HtmlContentType htmlContentType,
	    final SlotContentType slotContentType) {
	registry.register(htmlContentType);
	registry.register(slotContentType);
    }
}
