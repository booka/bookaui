package net.boklab.core.client.ui.browser;

import net.boklab.core.client.ui.action.Action;

public interface BrowserAction<B, I> extends Action {

    static enum VisibilityPolicy {
	always, session, selected
    }

    void setPresenter(B presenter);

    void setSelected(I selectedItemPresenter);
}
