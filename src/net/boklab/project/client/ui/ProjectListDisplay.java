package net.boklab.project.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface ProjectListDisplay extends Display {

    void add(ProjectDisplay projectDisplay);

    void clearList();

    ProjectDisplay createProjectDisplay();

}
