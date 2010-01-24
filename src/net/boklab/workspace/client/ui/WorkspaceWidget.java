package net.boklab.workspace.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspaceWidget extends Composite implements WorkspaceDisplay {
    private final LayoutPanel dock;
    private Widget east;
    private Widget west;

    public WorkspaceWidget() {
	this.dock = new LayoutPanel();
	initWidget(dock);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setEast(Display display) {
	if (this.east != null)
	    dock.remove(east);
	this.east = display.asWidget();
	if (east != null) {
	    dock.add(east);
	    dock.setWidgetTopBottom(east, 0, PX, 0, PX);
	    dock.setWidgetRightWidth(east, 0, PX, 250, PX);
	    dock.animate(250);
	}
    }

    @Override
    public void setWest(Display display) {
	if (this.west != null)
	    dock.remove(west);
	this.west = display.asWidget();
	if (west != null) {
	    dock.add(west);
	    dock.setWidgetTopBottom(west, 0, PX, 0, PX);
	    dock.setWidgetLeftWidth(west, 0, PX, 250, PX);
	    dock.animate(250);
	}
    }

}
