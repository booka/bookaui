package net.boklab.document.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentManager extends AbstractBokManager {

    @Inject
    public DocumentManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.DOCUMENT, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nDocs.t.openDocumentUntitled();
	    }

	    @Override
	    public String open(final String title) {
		return I18nDocs.t.openDocument(title);
	    }
	});
    }

}
