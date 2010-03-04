package net.boklab.site.client;

import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SiteManager extends AbstractBokManager {

    @Inject
    public SiteManager(final EventBus eventBus, final PlainBokManager manager) {
	super("Site", eventBus, manager);
    }

    public void open(final boolean force) {
	open("1", I18nSite.t.siteTitle(), force);
    }

}
