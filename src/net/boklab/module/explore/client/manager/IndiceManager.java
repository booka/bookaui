package net.boklab.module.explore.client.manager;

import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.module.explore.client.I18nExplore;
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
		return I18nExplore.t.openIndiceUnknown();
	    }

	    @Override
	    public String open(final String title) {
		return I18nExplore.t.openIndiceUnknown();
	    }

	    @Override
	    public String update(final String title) {
		return I18nExplore.t.update();
	    }
	});
    }

    public void openIndiceOfProject(final Bok project, final BokOpenedHandler handler) {
	openChildOfProject(project, handler);
    }

}
