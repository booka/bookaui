package net.boklab.core.client;

import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.rest.Params;

public class BokToParams {

    public static Params encode(final Bok bok, final Params p) {
	p.put("bok[title]", bok.getTitle());
	p.put("bok[description]", bok.getDescription());
	p.put("bok[bok_type]", bok.getBokType());
	p.put("bok[parent_id]", "" + bok.getParentId());
	p.put("bok[user_id]", "" + bok.getUserId());
	p.put("bok[body]", bok.getBody());
	p.put("bok[content_type]", bok.getContentType());
	p.put("bok[position]", "" + bok.getPosition());
	return p;
    }

}
