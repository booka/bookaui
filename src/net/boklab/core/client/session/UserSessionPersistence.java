package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSessionJSO;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserSessionPersistence {

    private static final String RESOURCE = "user_sessions";
    private final RestServiceAsync service;
    private final Sessions sessions;

    @Inject
    public UserSessionPersistence(final Sessions sessions, final RestServiceAsync service) {
	this.sessions = sessions;
	this.service = service;

	sessions.addLoginRequestHandler(new LoginRequestHandler() {
	    @Override
	    public void onLogin(final LoginRequestEvent evt) {
		final Params params = new Params().With("user_session[login]", evt.getName()).With(
			"user_session[password]", evt.getPassword());

		service.create(RESOURCE, params, new RequestCallback() {
		    @Override
		    public void onError(final Request request, final Throwable exception) {

		    }

		    @Override
		    public void onResponseReceived(final Request request, final Response response) {
			loadUserSession(response);
		    }

		});
	    }
	});
    }

    public void requestUserSession() {
	GWT.log("Initial session request");
	service.getList(RESOURCE, null, new RequestCallback() {
	    @Override
	    public void onError(final Request request, final Throwable exception) {
	    }

	    @Override
	    public void onResponseReceived(final Request request, final Response response) {
		GWT.log("Processing session request: " + response.getText());
		loadUserSession(response);
	    }
	});
    }

    private void loadUserSession(final Response response) {
	final String text = response.getText();
	final UserSessionJSO userSession = JsonUtils.unsafeEval(text);
	GWT.log("User session: " + userSession.getUserId() + " > " + text);
	if (userSession.getUserId() != null) {
	    sessions.setLoggedIn(userSession);
	}
    }

}
