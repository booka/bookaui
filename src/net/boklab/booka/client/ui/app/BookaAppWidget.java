package net.boklab.booka.client.ui.app;

import static com.google.gwt.dom.client.Style.Unit.PX;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class BookaAppWidget extends Composite implements BookaAppDisplay {

    interface BookaAppWidgetUiBinder extends UiBinder<Widget, BookaAppWidget> {
    }

    private static BookaAppWidgetUiBinder uiBinder = GWT.create(BookaAppWidgetUiBinder.class);

    @UiField
    SimplePanel north;

    @UiField
    LayoutPanel center;

    private final SwitchLayoutPanel slideLayout;

    @Inject
    public BookaAppWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	this.slideLayout = new SwitchLayoutPanel();
	center.add(slideLayout);
	center.setWidgetLeftRight(slideLayout, 0, PX, 0, PX);
	center.setWidgetTopBottom(slideLayout, 0, PX, 0, PX);
	center.forceLayout();
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setContent(Display display) {
	slideLayout.add(display.asWidget());
    }

    @Override
    public void setNavigation(Display display) {
	north.setWidget(display.asWidget());
    }
}
