package net.boklab.site.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SiteManager extends AbstractBokManager {

    @Inject
    public SiteManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.SITE, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nSite.t.openSiteUntitled();
	    }

	    @Override
	    public String open(final String title) {
		return I18nSite.t.openSite(title);
	    }

	    @Override
	    public String update(final String title) {
		return I18nSite.t.update();
	    }
	});
    }

    public void open(final boolean force) {
	open("1", null, force);
    }

}
