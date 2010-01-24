package net.boklab.project.client.action;

import net.boklab.project.client.model.ProjectList;
import net.boklab.tools.client.dispatcher.ActionCallback;
import net.boklab.tools.client.dispatcher.ActionHandler;
import net.boklab.tools.client.dispatcher.ExecutionContext;

import com.google.gwt.core.client.GWT;

public class GetProjectListHandler implements ActionHandler<ProjectListAction, ActionCallback<ProjectList>> {

    @Override
    public void execute(ProjectListAction action, ActionCallback<ProjectList> callback, ExecutionContext context) {
	GWT.log("Execute!", null);
	callback.onSuccess(null);

    }

}
