package net.boklab.document.client.doc;

import net.boklab.tools.client.mvp.Display;

public interface DocumentDisplay extends Display {

    void add(Display display);

    void clear();

    int getBokCount();

    int getDisplayIndex(Display display);

    void insert(Display display, int beforeIndex);

    void remove(Display display);

    void setInfoDisplay(Display display);

}
