package net.boklab.core.client;

import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.rest.Params;

public class BokToParams {

    public static Params encode(final Bok bok, final Params p) {
	save(p, "bok[title]", bok.getTitle());
	save(p, "bok[description]", bok.getDescription());
	save(p, "bok[bok_type]", bok.getBokType());
	save(p, "bok[parent_id]", bok.getParentId());
	save(p, "bok[user_id]", bok.getUserId());
	save(p, "bok[body]", bok.getBody());
	save(p, "bok[content_type]", bok.getContentType());
	final String position = bok.getPosition() > 0 ? "" + bok.getPosition() : null;
	save(p, "bok[position]", position);
	return p;
    }

    private static void save(final Params p, final String name, final String value) {
	if (value != null) {
	    p.put(name, value);
	}
    }

}
