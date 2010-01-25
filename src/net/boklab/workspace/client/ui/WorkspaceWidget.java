package net.boklab.workspace.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;
import net.boklab.document.client.ui.DocumentDisplay;
import net.boklab.tools.client.mvp.Display;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class WorkspaceWidget extends Composite implements WorkspaceDisplay {
    private static final int DOCK_EAST = 200;
    private static final int DOCK_WEST = 250;

    private final LayoutPanel dock;
    private Widget left;
    private Widget right;
    private Widget center;

    public WorkspaceWidget() {
	this.dock = new LayoutPanel();
	dock.setStyleName("bk-Workspace");
	initWidget(dock);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setCenter(DocumentDisplay display) {
	if (this.center != null)
	    dock.remove(center);
	this.center = display.asWidget();
	if (center != null) {
	    dock.add(center);
	    dock.setWidgetTopBottom(center, 0, PX, 0, PX);
	    dock.setWidgetLeftRight(center, DOCK_WEST, PX, DOCK_EAST, PX);
	    dock.animate(250);
	}
    }

    @Override
    public void setCenterVisible(boolean visible) {

    }

    @Override
    public void setLeft(Display display) {
	if (this.left != null)
	    dock.remove(left);
	this.left = display.asWidget();
	if (left != null) {
	    Log.debug("WorkspaceWidget: set east");
	    dock.add(left);
	    dock.setWidgetTopBottom(left, 0, PX, 0, PX);
	    dock.setWidgetRightWidth(left, 0, PX, DOCK_EAST, PX);
	    dock.animate(250);
	}
    }

    @Override
    public void setRight(Display display) {
	Log.debug("WorkspaceWidget: west");
	if (this.right != null)
	    dock.remove(right);
	this.right = display.asWidget();
	if (right != null) {
	    Log.debug("WorkspaceWidget: setWest");
	    dock.add(right);
	    dock.setWidgetTopBottom(right, 0, PX, 0, PX);
	    dock.setWidgetLeftWidth(right, 0, PX, DOCK_WEST, PX);
	    dock.animate(250);
	}
    }

}
