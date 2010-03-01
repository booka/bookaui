package net.boklab.document.client.bok.action;

import java.util.ArrayList;

import net.boklab.document.client.bok.BokPresenter;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BokActionsPresenter implements Presenter<BokActionsDisplay> {
    private final BokActionsDisplay display;
    private final ArrayList<ActionDisplay> actions;
    private BokPresenter clipPresenter;

    @Inject
    public BokActionsPresenter(final BokActionsDisplay display) {
	this.display = display;
	actions = new ArrayList<ActionDisplay>();
    }

    public void add(final BokAction action) {
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
    public BokActionsDisplay getDisplay() {
	return display;
    }

    public void setPresenter(final BokPresenter clipPresenter) {
	this.clipPresenter = clipPresenter;
	if (clipPresenter != null) {
	    for (final ActionDisplay acDisplay : actions) {
		acDisplay.setVisible(acDisplay.getAction().isApplicable(clipPresenter));
	    }
	}

    }

}
