package net.boklab.testing;

import net.boklab.booka.client.ui.app.BookaAppDisplay;
import net.boklab.booka.client.ui.navigation.NavigationDisplay;
import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.browser.client.ui.DocumentItemDisplay;
import net.boklab.calendar.client.ui.CalendarDisplay;
import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.session.SessionsBridge;
import net.boklab.document.client.clip.ClipDisplay;
import net.boklab.document.client.doc.DocumentDisplay;
import net.boklab.document.client.info.DocInfoDisplay;
import net.boklab.document.client.info.edit.DocInfoEditorDisplay;
import net.boklab.document.client.info.view.DocInfoViewerDisplay;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.manager.DocumentsBridge;
import net.boklab.project.client.ui.ProjectDisplay;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.place.HistoryManager;
import net.boklab.tools.client.place.PlaceTokenizer;
import net.boklab.tools.client.place.RestPlaceTokenizer;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.binder.ScopedBindingBuilder;

public class BookaTester extends AbstractModule implements Module {

    public final RouterTester router;
    public final Injector injector;
    public final EventBusTester eventBus;
    private final HistoryManagerTester historyManager;

    public BookaTester() {
	this(null);
    }

    public BookaTester(final Object test) {
	GWTMockUtilities.disarm();
	eventBus = new EventBusTester();
	router = new RouterTester(eventBus);
	historyManager = new HistoryManagerTester();
	injector = Guice.createInjector(this);
	if (test != null) {
	    injector.injectMembers(test);
	}
    }

    public <T> T get(final Class<T> modelClass) {
	return injector.getInstance(modelClass);
    }

    public EventBusTester getEventBus() {
	return eventBus;
    }

    public RouterTester getRouter() {
	return router;
    }

    private <T extends Display> ScopedBindingBuilder addDisplay(final Class<T> type) {
	return bind(type).toProvider(DisplayProvider.get(type));
    }

    private <T> ScopedBindingBuilder addMock(final Class<T> type) {
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
	addDisplay(CalendarDisplay.class).in(Singleton.class);
	addDisplay(DocInfoDisplay.class);
	addDisplay(DocInfoViewerDisplay.class);
	addDisplay(DocInfoEditorDisplay.class);
    }

    private void configureManagers() {
	addMock(RestManager.class);
	bind(Documents.class).to(DocumentsBridge.class).in(Singleton.class);
	bind(Sessions.class).to(SessionsBridge.class).in(Singleton.class);
    }

    @Override
    protected void configure() {
	bind(BookaTester.class).toInstance(this);
	bind(EventBusTester.class).toInstance(eventBus);
	bind(EventBus.class).toInstance(eventBus);
	bind(HistoryManager.class).toInstance(historyManager);
	bind(Router.class).toInstance(router);
	bind(PlaceTokenizer.class).to(RestPlaceTokenizer.class).in(Singleton.class);
	configureManagers();
	configureDisplays();
    }
}
