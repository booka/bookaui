package net.boklab.tools.client.rest;

import net.boklab.tools.client.eventbus.EventBus;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;

/**
 * A REST Service with errors in event bus
 */
public class DefaultRestManager implements RestManager {
    private final RestServiceAsync service;
    private final EventBus eventBus;

    @Inject
    public DefaultRestManager(EventBus eventBus, RestServiceAsync service) {
	this.eventBus = eventBus;
	this.service = service;
    }

    @Override
    public void create(final String requestId, String resource, Params params, final RestCallback callback) {

	service.create(resource, params, new RequestCallback() {
	    @Override
	    public void onError(Request request, Throwable exception) {
		fireError(requestId, exception);
	    }

	    @Override
	    public void onResponseReceived(Request request, Response response) {
		handleResponse(requestId, response, callback);
	    }
	});
    }

    @Override
    public void get(final String requestId, String resource, String id, final RestCallback callback) {
	service.get(resource, id, new RequestCallback() {
	    @Override
	    public void onError(Request request, Throwable exception) {
		fireError(requestId, exception);
	    }

	    @Override
	    public void onResponseReceived(Request request, Response response) {
		handleResponse(requestId, response, callback);
	    }
	});
    }

    @Override
    public String getHostPath() {
	return service.getHostPath();
    }

    @Override
    public void getList(final String requestId, String resource, Params params, final RestCallback callback) {
	service.getList(resource, params, new RequestCallback() {

	    @Override
	    public void onError(Request request, Throwable exception) {
		fireError(requestId, exception);
	    }

	    @Override
	    public void onResponseReceived(Request request, Response response) {
		handleResponse(requestId, response, callback);
	    }
	});
    }

    @Override
    public void setHostPath(String hostPath) {
	service.setHostPath(hostPath);
    }

    @Override
    public void update(final String requestId, String resource, String id, Params params, final RestCallback callback) {

	service.update(resource, id, params, new RequestCallback() {
	    @Override
	    public void onError(Request request, Throwable exception) {
		fireError(requestId, exception);
	    }

	    @Override
	    public void onResponseReceived(Request request, Response response) {
		handleResponse(requestId, response, callback);
	    }
	});
    }

    protected void fireError(String requestId, Throwable exception) {
	eventBus.fireEvent(new RestRequestErrorEvent(requestId, exception));
    }

    protected void handleResponse(String requestId, Response response, RestCallback callback) {
	String text = response.getText();
	Log.debug("RESPONSE (" + requestId + ")" + text);
	callback.onSuccess(text);
    }

}
