package net.boklab.workspace.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface WorkspaceDisplay extends Display {

    void remove(Display display);

    void setCenter(Display display);

    void setLeft(Display display);

    void setRight(Display display);

    void setVisible(boolean visible);

}
