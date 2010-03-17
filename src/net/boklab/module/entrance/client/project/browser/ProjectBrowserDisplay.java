package net.boklab.module.entrance.client.project.browser;

import net.boklab.tools.client.mvp.Display;

public interface ProjectBrowserDisplay extends Display {

    void add(ProjectDisplay projectDisplay);

    void clearList();

    ProjectDisplay createProjectDisplay();

}
