package net.boklab.document.client.manager;

import net.boklab.core.client.BokToParams;
import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.BokQuery;
import net.boklab.core.client.model.BokRequestResultsJSO;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentsPersistence extends Persistence<Document> {

    @Inject
    public DocumentsPersistence(final EventBus eventBus, final RestManager manager) {
	super(eventBus, manager, Document.TYPE);
    }

    @Override
    protected void create(final Document document) {
	final Params params = BokToParams.encode(document, new Params());
	manager.create("documents.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokJSO bok = JsonUtils.unsafeEval(text);
		final Document document = new Document(bok);
		final Document Document = new Document(document, null);
		eventBus.fireEvent(new DocumentOpenedEvent(Document));
	    }
	});
    }

    @Override
    protected void get(final String documentId) {
	final BokQuery query = new BokQuery();
	query.bokParentEquals(documentId);
	query.bokTypeEquals(Clip.TYPE);
	final Params params = query.toParams();
	final String url = RESOURCE + "/" + documentId + "/children";

	manager.getList("documents.clips", url, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {
		final BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		final Document document = new Document(results.getBok());
		final Document Document = new Document(document, results);
		eventBus.fireEvent(new DocumentOpenedEvent(Document));
	    }
	});
    }

    @Override
    protected void update(final Document document) {
	final Params params = BokToParams.encode(document, new Params());
	manager.update("documents.update", RESOURCE, document.getIdString(), params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {

	    }
	});
    }

}
