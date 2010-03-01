package net.boklab.workspace.client.ui;

import static com.google.gwt.dom.client.Style.Unit.PX;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class WorkspaceWidget extends Composite implements WorkspaceDisplay {
    private static final int DOCK_EAST = 200;
    private static final int DOCK_WEST = 250;

    private final LayoutPanel dock;

    public WorkspaceWidget() {
	dock = new LayoutPanel();
	dock.setStyleName("bk-Workspace");
	initWidget(dock);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void remove(final Display display) {
	dock.remove(display.asWidget());
    }

    @Override
    public void setCenter(final Display display) {
	final Widget center = display.asWidget();
	dock.add(center);
	dock.setWidgetTopBottom(center, 0, PX, 0, PX);
	dock.setWidgetLeftRight(center, DOCK_WEST, PX, DOCK_EAST, PX);
	dock.animate(250);
    }

    @Override
    public void setLeft(final Display display) {
	final Widget widget = display.asWidget();
	dock.add(widget);
	dock.setWidgetTopBottom(widget, 0, PX, 0, PX);
	dock.setWidgetLeftWidth(widget, 0, PX, DOCK_WEST, PX);
	dock.animate(250);
    }

    @Override
    public void setRight(final Display display) {
	final Widget widget = display.asWidget();
	dock.add(widget);
	dock.setWidgetTopBottom(widget, 0, PX, 0, PX);
	dock.setWidgetRightWidth(widget, 0, PX, DOCK_EAST, PX);
	dock.animate(250);
    }

}
