package net.boklab.document.client.actions;

import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginToEditAction extends BokAction {
    private static final String TYPE = "LoginToEdit";
    private final Sessions sessions;
    private final EventBus eventBus;

    @Inject
    public LoginToEditAction(final EventBus eventBus, final Sessions sessions) {
	super(TYPE, I18nDocs.t.loginToEdit());
	this.eventBus = eventBus;
	this.sessions = sessions;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	presenter.setEditor(null);
	eventBus.fireEvent(new PlaceRequestEvent(new Place("login")));
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return !sessions.isLoggedIn();
    }

}
