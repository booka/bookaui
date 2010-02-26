package net.boklab.document.client.clip.action;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class ClipActionsWidget extends FlowPanel implements ClipActionsDisplay {

    private static class ActionWidget extends Anchor implements ActionDisplay {
	private final ClipAction action;

	public ActionWidget(final ClipAction action) {
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
	public ClipAction getAction() {
	    return action;
	}

    }

    public ClipActionsWidget() {
	setStyleName("bk-ClipActions");
	ensureDebugId("bk-ClipActions");
    }

    @Override
    public ActionDisplay addAction(final ClipAction action) {
	final ActionWidget actionWidget = new ActionWidget(action);
	add(actionWidget);
	return actionWidget;
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
