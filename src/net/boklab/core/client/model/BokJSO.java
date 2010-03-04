package net.boklab.core.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

public class BokJSO extends JavaScriptObject implements BokDTO {

    @Deprecated
    public static BokJSO newInstance(final String bokType) {
	return JsonUtils.unsafeEval("{'bok':{bok_type = '+ bokType + '}}");
    }

    protected BokJSO() {
    }

    @Override
    public final BokJSO createBok(final String type, final String title, final String parentId, final String userId,
	    final int position) {
	final BokJSO bok = JsonUtils.unsafeEval("{'bok':{}}");
	bok.setBokType(type);
	bok.setTitle(title);
	bok.setUserId(userId);
	bok.setParentId(parentId);
	bok.setPosition(position);
	return bok;

    }

    public final native String getBody() /*-{
        return this.bok.body;
    }-*/;

    public final native String getBokType() /*-{
        return this.bok.bok_type;
    }-*/;

    public final native String getContentType() /*-{
        return this.bok.content_type;
    }-*/;

    public final native String getDescription() /*-{
        return this.bok.description;
    }-*/;

    public final native String getId() /*-{
        return "" + this.bok.id;
    }-*/;

    public final native String getParentId() /*-{
        return "" + this.bok.parent_id;
    }-*/;

    public final native int getPosition() /*-{
        return this.bok.position != null ? this.bok.position : -1;
    }-*/;

    public final native String getTitle() /*-{
        return this.bok.title;
    }-*/;

    public final native String getUpdatedAt() /*-{
        return this.bok.updated_at;
    }-*/;

    public final native String getUserId() /*-{
        return "" + this.bok.user_id;
    }-*/;

    public final native String getUserName() /*-{
        return this.bok.user_name;
    }-*/;

    public final native String getWrapperType() /*-{
        return 'bokJSO';
    }-*/;

    public final native void setBody(String body) /*-{
        this.bok.body= body;
    }-*/;

    public final native void setBokType(String type) /*-{
        this.bok.bok_type = type;
    }-*/;

    public final native void setContentType(String contentType) /*-{
        this.bok.content_type = contentType;
    }-*/;

    public final native void setDescription(String text) /*-{
        this.bok.description = text;
    }-*/;

    public final native void setParentId(String id) /*-{
        this.bok.parent_id = id;
    }-*/;

    public final native void setPosition(int position) /*-{
        this.bok.position = position;
    }-*/;

    public final native void setTitle(String title) /*-{
        this.bok.title = title;
    }-*/;

    public final native void setUserId(String id) /*-{
        this.bok.user_id = id;
    }-*/;

}
