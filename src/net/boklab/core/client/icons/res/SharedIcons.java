package net.boklab.core.client.icons.res;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SharedIcons extends Composite {

    interface SharedIconsUiBinder extends UiBinder<Widget, SharedIcons> {
    }

    private static SharedIconsUiBinder uiBinder = GWT.create(SharedIconsUiBinder.class);

    @UiField
    public SharedCssIcons style;

    public SharedIcons() {
	initWidget(uiBinder.createAndBindUi(this));
    }

}
