package net.boklab.workspace.client.ui;

import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WorkspacePresenter extends AbstractPresenter<WorkspaceDisplay> {
    @Inject
    public WorkspacePresenter(WorkspaceDisplay display) {
	super(display);
    }

    public void setEast(Presenter<? extends Display> presenter) {
	display.setEast(presenter.getDisplay());
    }
}
