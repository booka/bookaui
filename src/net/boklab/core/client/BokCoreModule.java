package net.boklab.core.client;

import net.boklab.core.client.persistence.BokPersistence;
import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.session.UserSessionManager;
import net.boklab.core.client.session.UserSessionPersistence;
import net.boklab.core.client.ui.insert.InsertDisplay;
import net.boklab.core.client.ui.insert.InsertWidget;
import net.boklab.core.client.ui.wip.WipDisplay;
import net.boklab.core.client.ui.wip.WipWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokCoreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(UserSessionPersistence.class).asEagerSingleton();
	bind(BokPersistence.class).asEagerSingleton();
	bind(Sessions.class).to(UserSessionManager.class);

	bind(WipDisplay.class).to(WipWidget.class);
	bind(InsertDisplay.class).to(InsertWidget.class);
    }

}
