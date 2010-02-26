package net.boklab.document.client.clip.action;

import java.util.ArrayList;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipActionsPresenter implements Presenter<ClipActionsDisplay> {
    private final ClipActionsDisplay display;
    private final ArrayList<ActionDisplay> actions;
    private ClipPresenter clipPresenter;

    @Inject
    public ClipActionsPresenter(final ClipActionsDisplay display) {
	this.display = display;
	actions = new ArrayList<ActionDisplay>();
    }

    public void add(final ClipAction action) {
	final ActionDisplay actionDisplay = display.addAction(action);
	actionDisplay.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(clipPresenter);
	    }
	});
	actions.add(actionDisplay);
    }

    @Override
    public ClipActionsDisplay getDisplay() {
	return display;
    }

    public void setPresenter(final ClipPresenter clipPresenter) {
	this.clipPresenter = clipPresenter;
	if (clipPresenter != null) {
	    for (final ActionDisplay acDisplay : actions) {
		acDisplay.setVisible(acDisplay.getAction().isApplicable(clipPresenter));
	    }
	}

    }

}
