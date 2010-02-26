package net.boklab.document.client.actions;

import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginToEditAction extends ClipAction {
    private static final String TYPE = "LoginToEdit";
    private final Sessions sessions;

    @Inject
    public LoginToEditAction(final Sessions sessions) {
	super(TYPE, I18nDocs.t.loginToEdit());
	this.sessions = sessions;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	sessions.login("Test", "secret");
	presenter.setEditor(null);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return !sessions.isLoggedIn();
    }

}