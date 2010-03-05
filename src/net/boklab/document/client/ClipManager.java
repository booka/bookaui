package net.boklab.document.client;

import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipManager extends AbstractBokManager {

    private static final String MODEL_TYPE = "Clip";

    public static String getModelType() {
	return MODEL_TYPE;
    }

    @Inject
    public ClipManager(final EventBus eventBus, final PlainBokManager manager) {
	super(MODEL_TYPE, eventBus, manager);
    }

}
