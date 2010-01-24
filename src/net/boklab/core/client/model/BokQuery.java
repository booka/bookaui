package net.boklab.core.client.model;

import java.util.HashMap;
import java.util.Map;

import net.boklab.tools.client.rest.Params;

public class BokQuery {

    private final HashMap<String, String> query;

    public BokQuery() {
	query = new HashMap<String, String>();
    }

    public void bokParentEquals(String id) {
	query.put("parent_id_equals", "" + id);
    }

    public void bokTypeEquals(String bokType) {
	query.put("bok_type_equals", bokType);
    }

    public Map<String, String> getMap() {
	return query;
    }

    public Params toParams() {
	Params p = new Params();
	for (String key : query.keySet()) {
	    p.put("search[" + key + "]", query.get(key));
	}
	return p;

    }
}
