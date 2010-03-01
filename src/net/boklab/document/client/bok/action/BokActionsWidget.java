package net.boklab.document.client.bok.action;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class BokActionsWidget extends FlowPanel implements BokActionsDisplay {

    private static class ActionWidget extends Anchor implements ActionDisplay {
	private final BokAction action;

	public ActionWidget(final BokAction action) {
	    super(action.getLabel());
	    setStyleName("bk-ActionWidget");
	    ensureDebugId("bk-ActionWidget-" + action.getType());
	    this.action = action;
	}

	@Override
	public Widget asWidget() {
	    return this;
	}

	@Override
	public BokAction getAction() {
	    return action;
	}

    }

    public BokActionsWidget() {
	setStyleName("bk-ClipActions");
	ensureDebugId("bk-ClipActions");
    }

    @Override
    public ActionDisplay addAction(final BokAction action) {
	final ActionWidget actionWidget = new ActionWidget(action);
	add(actionWidget);
	return actionWidget;
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
