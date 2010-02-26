package net.boklab.core.client.model;

import java.util.HashMap;
import java.util.Map;

import net.boklab.tools.client.rest.Params;

public class BokQuery {

    private final HashMap<String, String> query;

    public BokQuery() {
	query = new HashMap<String, String>();
    }

    public BokQuery bokParentEquals(final String id) {
	query.put("parent_id_equals", "" + id);
	return this;
    }

    public BokQuery bokTypeEquals(final String bokType) {
	query.put("bok_type_equals", bokType);
	return this;
    }

    public Map<String, String> getMap() {
	return query;
    }

    public Params toParams() {
	final Params p = new Params();
	for (final String key : query.keySet()) {
	    p.put("search[" + key + "]", query.get(key));
	}
	return p;

    }
}
