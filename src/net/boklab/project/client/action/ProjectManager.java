package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;

public interface ProjectManager {
    public void getProjectList();

    public void onProjectList(ProjectListHandler handler);

    void getProjectDocuments(Project project);

    void onProjectDocuments(ProjectDocumentsHandler handler);

}
