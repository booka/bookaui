package net.boklab.site.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface ProjectBrowserDisplay extends Display {

    void add(ProjectDisplay projectDisplay);

    void clearList();

    ProjectDisplay createProjectDisplay();

}
