package net.boklab.module.explore.client.browser.actions;

import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.module.explore.client.I18nExplore;
import net.boklab.module.explore.client.browser.IndiceBrowserAction;
import net.boklab.module.explore.client.browser.IndiceBrowserPresenter;
import net.boklab.module.explore.client.pointer.PointerPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginToEditIndiceAction extends AbstractAction implements IndiceBrowserAction {

    @Inject
    public LoginToEditIndiceAction(final UserSessionManager sessions) {
	super(I18nExplore.t.actionLoginToEditIndice(), Icons.get(BokIcon.unlock), sessions);
    }

    @Override
    public void execute() {
	sessions.requestLogin();
    }

    @Override
    public void setPresenter(final IndiceBrowserPresenter indiceBrowserPresenter) {
    }

    @Override
    public void setSelected(final PointerPresenter selected) {

    }

    @Override
    protected void onSessionChanged() {
	setVisible(!sessions.isLoggedIn());
    }

}
