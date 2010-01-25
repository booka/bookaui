package net.boklab.calendar.client;

import net.boklab.calendar.client.ui.CalendarDisplay;
import net.boklab.calendar.client.ui.CalendarPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(CalendarModule.class)
public interface CalendarGinjector {
    CalendarDisplay getCalendarDisplay();

    CalendarPresenter getCalendarPresenter();
}
