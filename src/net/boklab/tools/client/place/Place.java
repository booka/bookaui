package net.boklab.tools.client.place;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Place in the history
 * 
 * @author David Peterson
 */
public class Place {
    public static final String CONTROLLER = "controller";
    public static final String ID = "id";

    private final Map<String, String> params;

    public Place() {
	this(null, null);
    }

    public Place(String controller) {
	this(controller, null);
    }

    public Place(String controller, String id) {
	this.params = new HashMap<String, String>();
	if (controller != null)
	    params.put(CONTROLLER, controller);
	if (id != null)
	    params.put(ID, id);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Place) {
	    Place req = (Place) obj;
	    return params.equals(req.params);
	}
	return false;
    }

    public String getParameter(String key) {
	return params.get(key);
    }

    public Set<String> getParameterNames() {
	if (params != null) {
	    return params.keySet();
	} else {
	    return Collections.emptySet();
	}
    }

    @Override
    public int hashCode() {
	return 11 * (params.hashCode());
    }
}
