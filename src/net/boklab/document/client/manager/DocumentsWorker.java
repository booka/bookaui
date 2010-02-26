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
    public DocumentsWorker(final EventBus eventBus, final RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;

	eventBus.addHandler(OpenDocumentEvent.TYPE, new OpenDocumentHandler() {
	    @Override
	    public void onOpenDocument(final OpenDocumentEvent event) {
		open(event.getDocumentId());
	    }
	});

	eventBus.addHandler(CreateDocumentEvent.TYPE, new CreateDocumentHandler() {
	    @Override
	    public void onCreateDocument(final CreateDocumentEvent event) {
		create(event.getDocument());
	    }
	});

	eventBus.addHandler(UpdateDocumentEvent.TYPE, new UpdateDocumentHandler() {
	    @Override
	    public void onUpdateDocument(final UpdateDocumentEvent event) {
		update(event.getDocument());
	    }
	});

	eventBus.addHandler(CreateClipEvent.TYPE, new CreateClipHandler() {
	    @Override
	    public void onCreateClip(final CreateClipEvent event) {
		createClip(event.getDocument(), event.getClip());
	    }
	});
    }

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

    protected void createClip(final Document document, final Clip clip) {
	clip.setParentId(document.getParentId());
	final Params params = BokToParams.encode(clip, new Params());
	manager.create("document.clip.create", RESOURCE, params, new RestCallback() {
	    @Override
	    public void onSuccess(final String content) {
	    }
	});
    }

    protected void open(final String documentId) {
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

    protected void update(final Document document) {
	final Params params = BokToParams.encode(document, new Params());
	manager.update("documents.update", RESOURCE, document.getIdString(), params, new RestCallback() {
	    @Override
	    public void onSuccess(final String text) {

	    }
	});
    }

}
