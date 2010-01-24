package net.boklab.core.client;

import com.google.gwt.core.client.JavaScriptObject;

public class BokRequestResultsJSO extends JavaScriptObject implements BokSearchResults {

    protected BokRequestResultsJSO() {
    }

    @Override
    public final native BokJSO getBok() /*-{
        return this[0];
    }-*/;

    @Override
    public final native BokJSO getChildren(int index) /*-{
        return this[1]['boks'][index];
    }-*/;

    @Override
    public final native int getChildrenSize() /*-{
        return this[1]['boks'].length;
    }-*/;
}
