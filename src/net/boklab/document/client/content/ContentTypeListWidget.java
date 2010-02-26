package net.boklab.document.client.content;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ContentTypeListWidget extends FlowPanel implements ContentTypeListDisplay {

    public ContentTypeListWidget() {
	setStyleName("bk-ContentTypeList");
	ensureDebugId("ContentTypeListWidget");
    }

    @Override
    public HasClickHandlers addContentType(final String name) {
	final Label label = new Label(name);
	label.setStyleName("contentType");
	label.ensureDebugId("contentType-" + name);
	add(label);
	return label;
    }

    @Override
    public Widget asWidget() {
	return this;
    }
}
