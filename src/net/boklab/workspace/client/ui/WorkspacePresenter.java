package net.boklab.workspace.client.ui;

import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class WorkspacePresenter extends AbstractPresenter<WorkspaceDisplay> {
    @Inject
    public WorkspacePresenter(Provider<WorkspaceDisplay> displayProvider) {
	super(displayProvider);
    }

    public void setEast(Presenter<? extends Display> presenter) {
	Display child = presenter == null ? null : presenter.getDisplay();
	getDisplay().setLeft(child);
    }

    public void setWest(Presenter<? extends Display> presenter) {
	Display child = presenter == null ? null : presenter.getDisplay();
	getDisplay().setRight(child);
    }
}
