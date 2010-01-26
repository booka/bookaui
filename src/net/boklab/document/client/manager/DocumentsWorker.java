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
public class DocumentsWorker {
    private static final String RESOURCE = "boks";

    private final RestManager manager;
    private final EventBus eventBus;

    @Inject
    public DocumentsWorker(EventBus eventBus, RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;

	eventBus.addHandler(OpenDocumentEvent.TYPE, new OpenDocumentHandler() {
	    @Override
	    public void onOpenDocument(OpenDocumentEvent event) {
		open(event.getDocumentId());
	    }
	});

	eventBus.addHandler(CreateDocumentEvent.TYPE, new CreateDocumentHandler() {
	    @Override
	    public void onCreateDocument(CreateDocumentEvent event) {
		create(event.getDocument());
	    }
	});

	eventBus.addHandler(UpdateDocumentEvent.TYPE, new UpdateDocumentHandler() {
	    @Override
	    public void onUpdateDocument(Document document) {
		update(document);
	    }
	});
    }

    protected void create(final Document document) {
	Params params = BokToParams.encode(document, new Params());
	manager.create("documents.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokJSO bok = JsonUtils.unsafeEval(text);
		Document document = new Document(bok);
		Document Document = new Document(document, null);
		eventBus.fireEvent(new DocumentOpenedEvent(Document));
	    }
	});
    }

    protected void open(String documentId) {
	BokQuery query = new BokQuery();
	query.bokParentEquals(documentId);
	query.bokTypeEquals(Clip.TYPE);
	Params params = query.toParams();
	String url = RESOURCE + "/" + documentId + "/children";

	manager.getList("documents.clips", url, params, new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		Document document = new Document(results.getBok());
		Document Document = new Document(document, results);
		eventBus.fireEvent(new DocumentOpenedEvent(Document));
	    }
	});
    }

    protected void update(Document document) {
	Params params = BokToParams.encode(document, new Params());
	manager.update("documents.update", RESOURCE, document.getIdString(), params,
		new RestCallback() {
		    @Override
		    public void onSuccess(String text) {

		    }
		});
    }

}
