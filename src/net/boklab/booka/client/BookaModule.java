package net.boklab.booka.client;

import net.boklab.workspace.client.ui.app.BookaAppDisplay;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.app.BookaAppWidget;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BookaModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BookaAppDisplay.class).to(BookaAppWidget.class);
	bind(NavigationDisplay.class).to(NavigationWidget.class);
	bind(BookaAppPresenter.class).in(Singleton.class);

	bind(BokEntranceInstaller.class).asEagerSingleton();
	bind(BokArchivesInstaller.class).asEagerSingleton();

    }

}
