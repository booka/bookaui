package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSessionJSO;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestServiceAsync;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SessionsWorker {

    private static final String RESOURCE = "user_sessions";

    @Inject
    public SessionsWorker(final Sessions sessions, final RestServiceAsync service) {
	sessions.onLogin(new LoginHandler() {
	    @Override
	    public void onLogin(LoginEvent evt) {
		Params params = new Params().With("user[name]", evt.getName()).With(
			"user[password]", evt.getPassword());

		service.create(RESOURCE, params, new RequestCallback() {
		    @Override
		    public void onError(Request request, Throwable exception) {

		    }

		    @Override
		    public void onResponseReceived(Request request, Response response) {
			String text = response.getText();
			UserSessionJSO userSessionJSO = JsonUtils.unsafeEval(text);
			sessions.setLoggedIn(userSessionJSO);
		    }
		});
	    }
	});
    }
}
