package net.boklab.user.client.ui;

import net.boklab.tools.client.mvp.Presenter;
import net.boklab.user.client.ui.login.LoginPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserPresenter implements Presenter<UserDisplay> {

    private final UserDisplay display;

    @Inject
    public UserPresenter(final UserDisplay display, final LoginPresenter login) {
	this.display = display;
	display.setLogin(login.getDisplay());
    }

    @Override
    public UserDisplay getDisplay() {
	return display;
    }

}
