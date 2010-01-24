package net.boklab.project.client.action;

public interface Projects {
    public void getProjectList();

    public void onProjectList(GotProjectsHandler handler);

    void openProject(String projectId);

    void onProjectOpened(ProjectOpenedHandler handler);

}
