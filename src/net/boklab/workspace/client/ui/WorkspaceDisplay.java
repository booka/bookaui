package net.boklab.workspace.client.ui;

import net.boklab.document.client.ui.DocumentDisplay;
import net.boklab.tools.client.mvp.Display;

public interface WorkspaceDisplay extends Display {

    void setCenter(DocumentDisplay display);

    void setCenterVisible(boolean visible);

    void setLeft(Display display);

    void setRight(Display display);

}
