package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.EventHandler;

public interface ProjectListHandler extends EventHandler {

    void onProjectList(ArrayList<Project> list);

}
