package net.boklab.testing;

import net.boklab.booka.client.ui.app.BookaAppDisplay;
import net.boklab.booka.client.ui.navigation.NavigationDisplay;
import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.browser.client.ui.DocumentItemDisplay;
import net.boklab.document.client.manager.DefaultDocumentManager;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.document.client.ui.DocumentDisplay;
import net.boklab.document.client.ui.clip.ClipDisplay;
import net.boklab.project.client.action.DefaultProjectManager;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectDisplay;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.tools.client.eventbus.DefaultEventBus;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.place.HistoryManager;
import net.boklab.tools.client.place.PlaceTokenizer;
import net.boklab.tools.client.place.RestPlaceTokenizer;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.binder.ScopedBindingBuilder;

public class BookaTester extends AbstractModule implements Module {

    public final RouterTester router;
    public final Injector injector;
    public final DefaultEventBus eventBus;
    private final HistoryManagerTester historyManager;

    public BookaTester() {
	eventBus = new DefaultEventBus();
	router = new RouterTester(eventBus);
	historyManager = new HistoryManagerTester();
	injector = Guice.createInjector(this);
    }

    public <T> T get(Class<T> modelClass) {
	return injector.getInstance(modelClass);
    }

    public DefaultEventBus getEventBus() {
	return eventBus;
    }

    public RouterTester getRouter() {
	return router;
    }

    private <T extends Display> ScopedBindingBuilder addDisplay(Class<T> type) {
	return bind(type).toProvider(DisplayProvider.get(type));
    }

    private <T> ScopedBindingBuilder addMock(Class<T> type) {
	return bind(type).toProvider(MockProvider.get(type));
    }

    private void configureDisplays() {
	addDisplay(BookaAppDisplay.class).in(Singleton.class);
	addDisplay(NavigationDisplay.class).in(Singleton.class);
	addDisplay(WorkspaceDisplay.class).in(Singleton.class);
	addDisplay(ProjectListDisplay.class).in(Singleton.class);
	addDisplay(ProjectDisplay.class);
	addDisplay(DocumentBrowserDisplay.class).in(Singleton.class);
	addDisplay(DocumentItemDisplay.class);
	addDisplay(DocumentDisplay.class).in(Singleton.class);
	addDisplay(ClipDisplay.class);
    }

    private void configureManagers() {
	addMock(RestManager.class);
	bind(DocumentManager.class).to(DefaultDocumentManager.class).in(Singleton.class);
	bind(ProjectManager.class).to(DefaultProjectManager.class).in(Singleton.class);
    }

    @Override
    protected void configure() {
	bind(HistoryManager.class).toInstance(historyManager);
	bind(Router.class).toInstance(router);
	bind(EventBus.class).toInstance(eventBus);
	bind(PlaceTokenizer.class).to(RestPlaceTokenizer.class).in(Singleton.class);
	configureManagers();
	configureDisplays();
    }
}
