package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppDisplay;
import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.booka.client.ui.app.BookaAppWidget;
import net.boklab.booka.client.ui.archives.ArchivesPresenter;
import net.boklab.booka.client.ui.entrance.EntrancePresenter;
import net.boklab.booka.client.ui.navigation.NavigationDisplay;
import net.boklab.booka.client.ui.navigation.NavigationWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BookaModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BookaAppDisplay.class).to(BookaAppWidget.class);
	bind(NavigationDisplay.class).to(NavigationWidget.class);
	bind(BookaAppPresenter.class).in(Singleton.class);

	bind(EntrancePresenter.class).asEagerSingleton();
	bind(ArchivesPresenter.class).asEagerSingleton();

    }

}
