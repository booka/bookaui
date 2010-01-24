package net.boklab.document.client;

import net.boklab.document.client.manager.DocumentManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;

public class DocumentRouter {

    @Inject
    public DocumentRouter(Router router, final DocumentManager manager) {
	router.onRequest("^/documents/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		String documentId = event.getPlace().resourceId;
		manager.getDocumentClips(documentId);
	    }
	});
    }
}
