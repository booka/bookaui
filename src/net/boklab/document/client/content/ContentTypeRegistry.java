package net.boklab.document.client.content;

import java.util.HashMap;
import java.util.Iterator;

import net.boklab.document.client.content.html.HtmlContentHandler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ContentTypeRegistry implements Iterable<ContentHandler> {

    private final HashMap<String, ContentHandler> types;

    @Inject
    public ContentTypeRegistry() {
	types = new HashMap<String, ContentHandler>();
    }

    public ContentHandler getType(final String contentType) {
	final ContentHandler type = types.get(contentType);
	return type != null ? type : types.get(HtmlContentHandler.TYPE);
    }

    @Override
    public Iterator<ContentHandler> iterator() {
	return types.values().iterator();
    }

    public void register(final ContentHandler contentType) {
	types.put(contentType.getType(), contentType);
    }
}
