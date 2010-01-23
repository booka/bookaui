package net.boklab.projects.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ProjectListWidget extends Composite implements ProjectListDisplay {

    interface ProjectListWidgetUiBinder extends UiBinder<Widget, ProjectListWidget> {
    }

    private static ProjectListWidgetUiBinder uiBinder = GWT.create(ProjectListWidgetUiBinder.class);

    @UiField
    FlowPanel list;

    @Inject
    public ProjectListWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(ProjectDisplay projectDisplay) {
	list.add(projectDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void clearList() {
	list.clear();
    }

    @Override
    public ProjectDisplay createProjectDisplay() {
	ProjectWidget projectWidget = new ProjectWidget();
	return projectWidget;
    }

}
