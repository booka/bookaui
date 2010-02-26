package net.boklab.core.client.icons;

import java.util.HashMap;

public class Icons {

    private static HashMap<String, String> registry = new HashMap<String, String>();

    public static String get(final Object name) {
	return registry.get(name.toString());
    }

    public static void set(final Object name, final String css) {
	registry.put(name.toString(), css);
    }
}
