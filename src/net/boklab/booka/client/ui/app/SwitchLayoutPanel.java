package net.boklab.booka.client.ui.app;

import static com.google.gwt.dom.client.Style.Unit.PCT;
import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.Iterator;

import com.google.gwt.layout.client.Layout.AnimationCallback;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class SwitchLayoutPanel extends Composite implements HasWidgets {

    public static enum Transition {
	slideRight
    }

    private final LayoutPanel panel;
    private Widget currentWidget;

    public SwitchLayoutPanel() {
	panel = new LayoutPanel();
	initWidget(panel);
	currentWidget = null;
    }

    @Override
    public void add(Widget widget) {
	if (currentWidget == widget) {
	    return;
	}

	panel.add(widget);
	panel.setWidgetTopBottom(widget, 0, PX, 0, PX);
	panel.setWidgetLeftWidth(widget, 0, PX, 0, PCT);
	panel.forceLayout();

	if (currentWidget != null) {
	    panel.setWidgetRightWidth(currentWidget, 0, PX, 0, PX);
	}
	panel.setWidgetLeftWidth(widget, 0, PX, 100, PCT);
	final Widget old = currentWidget;
	currentWidget = widget;
	panel.animate(500, new AnimationCallback() {
	    @Override
	    public void onAnimationComplete() {
		if (old != null)
		    panel.remove(old);
	    }

	    @Override
	    public void onLayout(Layer layer, double progress) {
	    }
	});

    }

    @Override
    public void clear() {
	assert false : "Not implemented";
    }

    @Override
    public Iterator<Widget> iterator() {
	assert false : "Not implemented";
	return null;
    }

    @Override
    public boolean remove(Widget w) {
	assert false : "Not implemented";
	return false;
    }

}
