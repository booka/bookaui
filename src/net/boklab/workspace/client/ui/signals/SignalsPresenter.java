package net.boklab.workspace.client.ui.signals;

import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Deprecated
public class SignalsPresenter implements Presenter<SignalsDisplay> {
    private final SignalsDisplay display;

    @Inject
    public SignalsPresenter(final SignalsDisplay display) {
	this.display = display;
    }

    public SignalsDisplay getDisplay() {
	return display;
    }

}
