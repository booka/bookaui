package net.boklab.document.client.content;

import java.util.HashMap;
import java.util.Iterator;

import net.boklab.document.client.content.html.HtmlContentType;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ContentTypeRegistry implements Iterable<ClipContentType> {

    private final HashMap<String, ClipContentType> types;

    @Inject
    public ContentTypeRegistry() {
	types = new HashMap<String, ClipContentType>();
    }

    public ClipContentType getType(final String contentType) {
	final ClipContentType type = types.get(contentType);
	return type != null ? type : types.get(HtmlContentType.TYPE);
    }

    @Override
    public Iterator<ClipContentType> iterator() {
	return types.values().iterator();
    }

    public void register(final ClipContentType contentType) {
	types.put(contentType.getType(), contentType);
    }
}
