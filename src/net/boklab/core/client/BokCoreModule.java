package net.boklab.core.client;

import net.boklab.core.client.persistence.BokPersistence;
import net.boklab.core.client.session.ClientSession;
import net.boklab.core.client.ui.insert.InsertDisplay;
import net.boklab.core.client.ui.insert.InsertWidget;
import net.boklab.core.client.ui.wip.WipDisplay;
import net.boklab.core.client.ui.wip.WipWidget;
import net.boklab.core.client.user.UserSessionPersistence;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokCoreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(UserSessionPersistence.class).asEagerSingleton();
	bind(ClientSession.class).asEagerSingleton();
	bind(BokPersistence.class).asEagerSingleton();

	bind(WipDisplay.class).to(WipWidget.class);
	bind(InsertDisplay.class).to(InsertWidget.class);
    }

}
