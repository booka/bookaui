package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;

public interface Projects {
    public void getProjectList();

    public boolean hasActiveProject();

    public void onProjectList(GotProjectsHandler handler);

    Project getActiveProject();

    void onProjectOpened(ProjectOpenedHandler handler);

    void openProject(String projectId);

}
