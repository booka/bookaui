package net.boklab.workspace.client.ui;

import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WorkspacePresenter implements Presenter<WorkspaceDisplay> {
    Presenter<? extends Display> center, left, right;
    private final WorkspaceDisplay display;

    @Inject
    public WorkspacePresenter(final WorkspaceDisplay display) {
	this.display = display;
	center = left = right = null;
    }

    @Override
    public WorkspaceDisplay getDisplay() {
	return display;
    }

    public void setCenter(final Presenter<? extends Display> presenter) {
	if (center != presenter) {
	    if (center != null) {
		display.remove(center.getDisplay());
	    }
	    center = presenter;
	    if (center != null) {
		display.setCenter(center.getDisplay());
	    }
	}
    }

    public void setLeft(final Presenter<? extends Display> presenter) {
	if (left != presenter) {
	    if (left != null) {
		display.remove(left.getDisplay());
	    }
	    left = presenter;
	    if (left != null) {
		display.setLeft(left.getDisplay());
	    }
	}
    }

    public void setRight(final Presenter<? extends Display> presenter) {
	if (right != presenter) {
	    if (right != null) {
		display.remove(right.getDisplay());
	    }
	    right = presenter;
	    if (right != null) {
		display.setRight(right.getDisplay());
	    }
	}
    }
}
