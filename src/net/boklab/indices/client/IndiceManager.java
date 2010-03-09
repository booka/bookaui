package net.boklab.indices.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndiceManager extends AbstractBokManager {

    @Inject
    public IndiceManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.INDICE, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nIndices.t.openIndiceUnknown();
	    }

	    @Override
	    public String open(final String title) {
		return I18nIndices.t.openIndiceUnknown();
	    }

	    @Override
	    public String update(final String title) {
		return I18nIndices.t.update();
	    }
	});
    }

}
