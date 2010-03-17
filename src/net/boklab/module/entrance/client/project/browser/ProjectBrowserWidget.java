package net.boklab.module.entrance.client.project.browser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectBrowserWidget extends Composite implements ProjectBrowserDisplay {

    interface ProjectListWidgetUiBinder extends UiBinder<Widget, ProjectBrowserWidget> {
    }

    private static ProjectListWidgetUiBinder uiBinder = GWT.create(ProjectListWidgetUiBinder.class);

    @UiField
    FlowPanel list;

    @Inject
    public ProjectBrowserWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void add(final ProjectDisplay projectDisplay) {
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
	final ProjectWidget projectWidget = new ProjectWidget();
	return projectWidget;
    }

}
