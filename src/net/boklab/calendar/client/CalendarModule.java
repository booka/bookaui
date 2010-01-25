package net.boklab.calendar.client;

import net.boklab.calendar.client.ui.CalendarDisplay;
import net.boklab.calendar.client.ui.CalendarPresenter;
import net.boklab.calendar.client.ui.CalendarWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class CalendarModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(CalendarDisplay.class).to(CalendarWidget.class);
	bind(CalendarPresenter.class).in(Singleton.class);
    }

}
