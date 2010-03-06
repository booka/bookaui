package net.boklab.core.client.ui.wip;

import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WipPresenter implements Presenter<WipDisplay> {

    private final WipDisplay display;

    @Inject
    public WipPresenter(final WipDisplay display) {
	this.display = display;
    }

    @Override
    public void bind() {
    }

    public WipDisplay getDisplay() {
	return display;
    }
}
