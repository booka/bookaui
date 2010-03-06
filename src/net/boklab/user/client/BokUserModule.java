package net.boklab.user.client;

import net.boklab.user.client.ui.SessionDisplay;
import net.boklab.user.client.ui.SessionWidget;
import net.boklab.user.client.ui.login.LoginDisplay;
import net.boklab.user.client.ui.login.LoginWidget;
import net.boklab.user.client.ui.logout.LogoutDisplay;
import net.boklab.user.client.ui.logout.LogoutWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokUserModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(SessionController.class).asEagerSingleton();
	bind(AccountController.class).asEagerSingleton();
	bind(SessionDisplay.class).to(SessionWidget.class);
	bind(LoginDisplay.class).to(LoginWidget.class);
	bind(LogoutDisplay.class).to(LogoutWidget.class);
    }

}
