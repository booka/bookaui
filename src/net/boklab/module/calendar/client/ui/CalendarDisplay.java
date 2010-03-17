package net.boklab.module.calendar.client.ui;

import net.boklab.tools.client.mvp.Display;

public interface CalendarDisplay extends Display {

    void setHTML(int row, int column, String html);

}
