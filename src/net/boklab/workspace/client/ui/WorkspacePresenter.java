package net.boklab.workspace.client.ui;

import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.core.client.GWT;
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
    public void bind() {
    }

    @Override
    public WorkspaceDisplay getDisplay() {
	return display;
    }

    public void setCenter(final Presenter<? extends Display> presenter) {
	if (center != presenter) {
	    GWT.log("Set center: " + (presenter != null ? presenter : "nothign"));
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
	    GWT.log("Set left: " + (presenter != null ? presenter : "nothing"));
	    if (left != null) {
		GWT.log("Remove left" + left.getDisplay());
		display.remove(left.getDisplay());
	    }
	    if (presenter == right) {
		setRight(null);
	    }
	    left = presenter;
	    if (left != null) {
		display.setLeft(left.getDisplay());
	    }
	}
    }

    public void setRight(final Presenter<? extends Display> presenter) {
	if (right != presenter) {
	    GWT.log("Set right: " + (presenter != null ? presenter : "nothign"));
	    if (right != null) {
		display.remove(right.getDisplay());
	    }
	    if (presenter == left) {
		setLeft(null);
	    }
	    right = presenter;
	    if (right != null) {
		display.setRight(right.getDisplay());
	    }
	}
    }

    public void setVisible(final boolean visible) {
	display.setVisible(visible);

    }
}
