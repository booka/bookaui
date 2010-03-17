package net.boklab.core.client.ui.browser;

import net.boklab.core.client.ui.action.Action;

public interface BrowserAction<B, I> extends Action {
    void setPresenter(B indiceBrowserPresenter);

    void setSelected(I selected);
}
