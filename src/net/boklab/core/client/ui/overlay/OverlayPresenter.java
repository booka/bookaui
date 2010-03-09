package net.boklab.core.client.ui.overlay;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class OverlayPresenter {
    private final PopupPanel popup;

    @Inject
    public OverlayPresenter() {
	popup = new PopupPanel();
	popup.setGlassEnabled(true);
	popup.setAnimationEnabled(true);
    }

    public void close() {
	popup.hide();
	popup.clear();
    }

    public void show(final Display display) {
	popup.setAutoHideEnabled(false);
	popup.setWidget(display.asWidget());
	popup.center();
    }

    public void showError(final String message) {
	popup.setAutoHideEnabled(true);
	final Label label = new Label(message);
	label.setStyleName("bk-Dialog");
	popup.setWidget(label);
	popup.center();
    }
}
