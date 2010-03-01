package net.boklab.workspace.client.ui.app;

import net.boklab.tools.client.mvp.Display;

public interface BookaAppDisplay extends Display {

    void setContent(Display display);

    void setEmite(Display display);

    void setNavigation(Display display);

}
