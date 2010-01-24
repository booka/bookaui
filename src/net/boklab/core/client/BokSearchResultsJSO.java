package net.boklab.core.client;

import com.google.gwt.core.client.JavaScriptObject;

public class BokSearchResultsJSO extends JavaScriptObject implements BokSearchResults {

    protected BokSearchResultsJSO() {
    }

    @Override
    public final native Bok get(int index) /*-{
        return this[index];
    }-*/;

    @Override
    public final native int getSize() /*-{
        return this.length;
    }-*/;
}
