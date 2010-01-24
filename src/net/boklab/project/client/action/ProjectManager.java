package net.boklab.project.client.action;

public interface ProjectManager {
    public void getProjectList();

    public void onProjectList(ProjectListHandler handler);

    void getProjectDocuments(String projectId);

    void onProjectDocuments(ProjectDocumentsHandler handler);

}
