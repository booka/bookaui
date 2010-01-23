package net.boklab.tools.client.rest;

import com.google.gwt.http.client.URL;

public class Params {
    private final StringBuffer buffer;
    private boolean isFirst;

    public Params() {
	buffer = new StringBuffer();
	isFirst = true;
    }

    public void put(String key, String value) {
	if (key != null && value != null) {
	    if (isFirst) {
		isFirst = false;
	    } else {
		buffer.append("&");
	    }
	    value = value == null ? "" : value;
	    buffer.append(URL.encode(key)).append("=").append(URL.encodeComponent(value));
	}
    }

    @Override
    public String toString() {
	return buffer.toString();
    }

    public Params With(String key, String value) {
	put(key, value);
	return this;
    }
}
