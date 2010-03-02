package net.boklab.document.client.content;

import java.util.HashMap;
import java.util.Iterator;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ContentHandlerRegistry implements Iterable<ContentHandler> {

    private final HashMap<String, ContentHandler> types;

    @Inject
    public ContentHandlerRegistry() {
	types = new HashMap<String, ContentHandler>();
    }

    public ContentHandler getHandler(final String contentType) {
	final ContentHandler type = types.get(contentType);
	return type;
    }

    @Override
    public Iterator<ContentHandler> iterator() {
	return types.values().iterator();
    }

    public void register(final ContentHandler contentType) {
	types.put(contentType.getType(), contentType);
    }
}
