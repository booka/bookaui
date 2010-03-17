package net.boklab.module.calendar.client;

import net.boklab.module.calendar.client.ui.CalendarDisplay;
import net.boklab.module.calendar.client.ui.CalendarPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(BokCalendarModule.class)
public interface BokCalendarGinjector {
    CalendarDisplay getCalendarDisplay();

    CalendarPresenter getCalendarPresenter();
}
