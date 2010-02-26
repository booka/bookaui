package net.boklab.document.client.doc;

import net.boklab.tools.client.mvp.Display;

public interface DocumentDisplay extends Display {

    void add(Display display);

    void clear();

    void insert(Display display, Display beforeDisplay);

    void remove(Display display);

    void setInfoDisplay(Display display);
}
