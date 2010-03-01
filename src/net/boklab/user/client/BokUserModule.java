package net.boklab.user.client;

import net.boklab.user.client.ui.UserDisplay;
import net.boklab.user.client.ui.UserWidget;
import net.boklab.user.client.ui.login.LoginDisplay;
import net.boklab.user.client.ui.login.LoginWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokUserModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BokUserInstaller.class).asEagerSingleton();

	bind(UserDisplay.class).to(UserWidget.class);
	bind(LoginDisplay.class).to(LoginWidget.class);
    }

}
