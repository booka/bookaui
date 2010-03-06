package net.boklab.document.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipManager extends AbstractBokManager {

    private static final String MODEL_TYPE = "Clip";

    public static String getModelType() {
	return MODEL_TYPE;
    }

    @Inject
    public ClipManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.CLIP, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nDocs.t.openClip();
	    }

	    @Override
	    public String open(final String title) {
		return I18nDocs.t.openClip();
	    }
	});
    }
}
