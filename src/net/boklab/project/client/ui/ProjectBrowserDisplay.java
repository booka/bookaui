package net.boklab.project.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface ProjectBrowserDisplay extends Display {

    void add(ProjectDisplay projectDisplay);

    void clearList();

    ProjectDisplay createProjectDisplay();

}
