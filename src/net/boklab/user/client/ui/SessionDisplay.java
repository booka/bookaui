package net.boklab.user.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface SessionDisplay extends Display {

    void addDisplay(Display display);

    void removeDisplay(Display display);

}
