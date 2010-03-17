package net.boklab.module.calendar.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokCalendarEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final CalendarMessages messages = GWT.create(CalendarMessages.class);
	I18nCalendar.setMessages(messages);
    }

}
