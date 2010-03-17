package net.boklab.module.calendar.client;

import net.boklab.module.calendar.client.ui.CalendarDisplay;
import net.boklab.module.calendar.client.ui.CalendarPresenter;
import net.boklab.module.calendar.client.ui.CalendarWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BokCalendarModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(CalendarController.class).asEagerSingleton();
	bind(CalendarDisplay.class).to(CalendarWidget.class);
	bind(CalendarPresenter.class).in(Singleton.class);
    }

}
