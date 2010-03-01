package net.boklab.calendar.client;

import net.boklab.calendar.client.ui.CalendarDisplay;
import net.boklab.calendar.client.ui.CalendarPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(BokCalendarModule.class)
public interface BokCalendarGinjector {
    CalendarDisplay getCalendarDisplay();

    CalendarPresenter getCalendarPresenter();
}
