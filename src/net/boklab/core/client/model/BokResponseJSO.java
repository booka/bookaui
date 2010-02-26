package net.boklab.core.client.model;

import com.google.gwt.core.client.JavaScriptObject;

public class BokResponseJSO extends JavaScriptObject implements BokResponse {

    protected BokResponseJSO() {
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

    @Override
    public final native BokJSO getUpdated(int index) /*-{
        return this[2]['updated'][index];
    }-*/;

    @Override
    public final native int getUpdatedSize() /*-{
        return this[2]['updated'].length;
    }-*/;

}
