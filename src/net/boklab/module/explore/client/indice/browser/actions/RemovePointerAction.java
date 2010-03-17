package net.boklab.module.explore.client.indice.browser.actions;

import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.module.explore.client.I18nExplore;
import net.boklab.module.explore.client.indice.browser.IndiceBrowserAction;
import net.boklab.module.explore.client.indice.browser.IndiceBrowserPresenter;
import net.boklab.module.explore.client.pointer.PointerPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RemovePointerAction extends AbstractAction implements IndiceBrowserAction {

    private IndiceBrowserPresenter presenter;

    @Inject
    public RemovePointerAction(final UserSessionManager sessions) {
	super(I18nExplore.t.actionRemovePointer(), Icons.get(BokIcon.none), sessions);
    }

    @Override
    public void execute() {
    }

    @Override
    public void setPresenter(final IndiceBrowserPresenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void setSelected(final PointerPresenter selected) {
	changeVisibility();
    }

    private void changeVisibility() {
	setVisible(sessions.isLoggedIn() && presenter != null && presenter.hasSelected());
    }

    @Override
    protected void onSessionChanged() {
	changeVisibility();
    }
}
