package net.boklab.site.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CallManager extends AbstractBokManager {

    @Inject
    public CallManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.CALL, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nSite.t.openCallUntitled();
	    }

	    @Override
	    public String open(final String title) {
		return I18nSite.t.openCall(title);
	    }
	});
    }

}
