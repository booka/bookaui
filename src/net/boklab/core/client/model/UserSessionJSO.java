package net.boklab.core.client.model;

import com.google.gwt.core.client.JavaScriptObject;

public class UserSessionJSO extends JavaScriptObject implements UserSession {

    protected UserSessionJSO() {
    }

    public final native String getToken() /*-{
        return this.token;
    }-*/;

    @Override
    public final native String getUserId() /*-{
        return this.user_id == null ? null : "" + this.user_id;
    }-*/;

    @Override
    public final native String getUserName() /*-{
        return this.user_name;
    }-*/;
}
