package net.boklab.project.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectWidget extends Composite implements ProjectDisplay {

    interface ProjectWidgetUiBinder extends UiBinder<Widget, ProjectWidget> {
    }

    private static ProjectWidgetUiBinder uiBinder = GWT.create(ProjectWidgetUiBinder.class);

    @UiField
    Anchor title;

    public ProjectWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasHTML getTitleHeader() {
	return title;
    }

}
