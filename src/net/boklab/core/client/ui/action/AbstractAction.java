package net.boklab.core.client.ui.action;

import net.boklab.core.client.user.SessionChangedEvent;
import net.boklab.core.client.user.SessionChangedHandler;
import net.boklab.core.client.user.UserSessionManager;

public abstract class AbstractAction implements Action {

    private boolean visible;
    private ActionDisplay display;
    private final String name;
    private final String iconStyle;
    protected final UserSessionManager sessions;

    public AbstractAction(final String name, final String iconStyle, final UserSessionManager sessions) {
	this.name = name;
	this.iconStyle = iconStyle;
	this.sessions = sessions;
	visible = false;
	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		AbstractAction.this.onSessionChanged();
	    }

	}, true);
    }

    @Override
    public String getIconStyle() {
	return iconStyle;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public boolean isVisible() {
	return visible;
    }

    @Override
    public void setDisplay(final ActionDisplay display) {
	this.display = display;
    }

    public void setVisible(final boolean visible) {
	this.visible = visible;
	if (display != null) {
	    display.setVisible(visible);
	}
    }

    protected void onSessionChanged() {

    }
}
