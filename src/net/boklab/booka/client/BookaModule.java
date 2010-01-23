package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppDisplay;
import net.boklab.booka.client.ui.app.BookaAppWidget;
import net.boklab.booka.client.ui.navigation.NavigationDisplay;
import net.boklab.booka.client.ui.navigation.NavigationWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BookaModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BookaAppDisplay.class).to(BookaAppWidget.class);
	bind(NavigationDisplay.class).to(NavigationWidget.class);
    }

}
