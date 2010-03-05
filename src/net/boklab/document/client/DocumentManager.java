package net.boklab.document.client;

import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentManager extends AbstractBokManager {

    @Inject
    public DocumentManager(final EventBus eventBus, final PlainBokManager manager) {
	super("Document", eventBus, manager);
    }

}
