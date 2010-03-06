package net.boklab.workspace.client.ui.signals;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class SignalsWidget extends Composite implements SignalsDisplay {

    interface SignalsWidgetUiBinder extends UiBinder<Widget, SignalsWidget> {
    }

    private static SignalsWidgetUiBinder uiBinder = GWT.create(SignalsWidgetUiBinder.class);

    @UiField
    Anchor user;
    @UiField
    Label place, project;
    @UiField
    FlowPanel messages;

    private final HashMap<Integer, Label> idToMessages;

    public SignalsWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	idToMessages = new HashMap<Integer, Label>();
    }

    @Override
    public void addMessage(final int id, final String body, final String level) {
	final Label m = new Label(body);
	m.addStyleName("message");
	m.addStyleName("level");
	messages.add(m);
	idToMessages.put(id, m);
	new Timer() {
	    @Override
	    public void run() {
		removeMessage(id);
	    }
	}.schedule(5000);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getPlace() {
	return place;
    }

    @Override
    public HasText getProject() {
	return project;
    }

    @Override
    public HasText getUser() {
	return user;
    }

    @Override
    public HasClickHandlers getUserAction() {
	return user;
    }

    @Override
    public boolean removeMessage(final int id) {
	final Label m = idToMessages.remove(id);
	if (m != null) {
	    messages.remove(m);
	    return true;
	}
	return false;
    }

    @Override
    public void setUserActive(final boolean active) {
	if (active) {
	    user.addStyleName("active");
	} else {
	    user.removeStyleName("active");
	}
    }

}
