package net.boklab.document.client.manager;

import net.boklab.core.client.BokToParams;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.BokQuery;
import net.boklab.core.client.model.BokRequestResultsJSO;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.model.DocumentClips;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;

public class DefaultDocumentManager implements DocumentManager {
    private static final String RESOURCE = "boks";

    public static void fireDocumentClips(EventBus eventBus, DocumentClips documentClips) {
	DocumentClipsEvent event = new DocumentClipsEvent(documentClips);
	eventBus.fireEvent(event);
    }

    private final RestManager manager;
    private final EventBus eventBus;

    @Inject
    public DefaultDocumentManager(EventBus eventBus, RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;
    }

    @Override
    public void createDocument(final Document document) {
	Params params = BokToParams.encode(document, new Params());
	manager.create("documents.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokJSO bok = JsonUtils.unsafeEval(text);
		Document document = new Document(bok);
		DefaultDocumentManager.fireDocumentClips(eventBus,
			new DocumentClips(document, null));
	    }
	});
    }

    @Override
    public void getDocumentClips(final String documentId) {
	BokQuery query = new BokQuery();
	query.bokParentEquals(documentId);
	query.bokTypeEquals(Clip.TYPE);
	Params params = query.toParams();
	manager.getList("documents.clips", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		Document document = new Document(results.getBok());
		DefaultDocumentManager.fireDocumentClips(eventBus, new DocumentClips(document,
			results));
	    }
	});
    }

    @Override
    public void onDocumentClips(DocumentClipsHandler handler) {
	eventBus.addHandler(DocumentClipsEvent.TYPE, handler);
    }

    @Override
    public void update(Document document) {
	Params params = BokToParams.encode(document, new Params());
	manager.update("documents.update", RESOURCE, document.getIdString(), params,
		new RestCallback() {
		    @Override
		    public void onSuccess(String text) {

		    }
		});
    }

}
