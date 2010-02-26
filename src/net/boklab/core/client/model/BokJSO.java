package net.boklab.core.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

public class BokJSO extends JavaScriptObject implements Bok {

    public static BokJSO newInstance(final String bokType) {
	return JsonUtils.unsafeEval("{'bok':{'bok_type': '" + bokType + "'}}");
    }

    protected BokJSO() {
    }

    @Override
    public final native String getBody() /*-{
        return this.bok.body;
    }-*/;

    @Override
    public final native String getBokType() /*-{
        return this.bok.bok_type;
    }-*/;

    @Override
    public final native String getContentType() /*-{
        return this.bok.content_type;
    }-*/;

    @Override
    public final native String getDescription() /*-{
        return this.bok.description;
    }-*/;

    @Override
    public final native String getId() /*-{
        return "" + this.bok.id;
    }-*/;

    @Override
    public final native String getParentId() /*-{
        return "" + this.bok.parent_id;
    }-*/;

    @Override
    public final native Integer getPosition() /*-{
        return this.bok.position;
    }-*/;

    @Override
    public final native String getTitle() /*-{
        return this.bok.title;
    }-*/;

    @Override
    public final native String getUpdatedAt() /*-{
        return this.bok.updated_at;
    }-*/;

    @Override
    public final native String getUserId() /*-{
        return "" + this.bok.user_id;
    }-*/;

    @Override
    public final native String getUserName() /*-{
        return this.bok.user_name;
    }-*/;

    @Override
    public final native void setBody(String body) /*-{
        this.bok.body= body;
    }-*/;

    @Override
    public final native void setBokType(String type) /*-{
        this.bok.bok_type = type;
    }-*/;

    @Override
    public final native void setContentType(String contentType) /*-{
        this.bok.content_type = contentType;
    }-*/;

    @Override
    public final native void setDescription(String text) /*-{
        this.bok.description = text;
    }-*/;

    @Override
    public final native void setParentId(String id) /*-{
        this.bok.parent_id = id;
    }-*/;

    @Override
    public final native void setPosition(int position) /*-{
        this.bok.position = position;
    }-*/;

    @Override
    public final native void setTitle(String title) /*-{
        this.bok.title = title;
    }-*/;

    @Override
    public final native void setUserId(String id) /*-{
        this.bok.user_id = id;
    }-*/;

}
