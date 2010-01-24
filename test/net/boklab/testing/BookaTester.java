package net.boklab.testing;

import net.boklab.browser.client.ui.DocumentItemDisplay;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.eventbus.DefaultEventBus;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class BookaTester extends AbstractModule implements Module {

    public final RouterTester router;
    public final Injector injector;
    public final DefaultEventBus eventBus;

    public BookaTester() {
	eventBus = new DefaultEventBus();
	router = new RouterTester(eventBus);
	injector = Guice.createInjector(this);
    }

    public <T> T get(Class<T> modelClass) {
	return injector.getInstance(modelClass);
    }

    private <T extends Display> void addDisplay(Class<T> type) {
	bind(type).toProvider(DisplayProvider.get(type));
    }

    private <T> void addMock(Class<T> type) {
	bind(type).toProvider(MockProvider.get(type));
    }

    @Override
    protected void configure() {
	bind(Router.class).toInstance(router);
	bind(EventBus.class).toInstance(eventBus);
	configureManagers();
	configureDisplays();
    }

    private void configureDisplays() {
	addDisplay(DocumentItemDisplay.class);
    }

    private void configureManagers() {
	addMock(RestManager.class);
	addMock(DocumentManager.class);
	addMock(ProjectManager.class);
    }

}
