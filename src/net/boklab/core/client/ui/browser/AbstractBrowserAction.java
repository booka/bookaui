package net.boklab.core.client.ui.browser;

import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.user.UserSessionManager;

public abstract class AbstractBrowserAction<B, I> extends AbstractAction implements
	BrowserAction<B, I> {

    protected B presenter;
    protected I selected;
    private VisibilityPolicy policy;

    public AbstractBrowserAction(final String name, final String iconStyle,
	    final UserSessionManager sessions) {
	super(name, iconStyle, sessions);
	setVisibility(VisibilityPolicy.session);
    }

    public VisibilityPolicy getVisibility() {
	return policy;
    }

    @Override
    public void setPresenter(final B presenter) {
	this.presenter = presenter;

    }

    @Override
    public void setSelected(final I selected) {
	this.selected = selected;
    }

    public void setVisibility(final VisibilityPolicy visibility) {
	this.policy = visibility;
	changeVisibility();
    }

    private void changeVisibility() {
	if (policy == VisibilityPolicy.always) {
	    setVisible(true);
	} else if (policy == VisibilityPolicy.session) {
	    setVisible(sessions.isLoggedIn());
	} else if (policy == VisibilityPolicy.selected) {
	    setVisible(sessions.isLoggedIn() && selected != null);
	} else {
	    setVisible(true);
	}
    };

    @Override
    protected void onSessionChanged() {
	changeVisibility();
    }

}
