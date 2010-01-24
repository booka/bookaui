package net.boklab.testing;

import net.boklab.browser.client.ui.DocumentItemDisplay;
import net.boklab.tools.client.eventbus.DefaultEventBus;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;

import org.mockito.Mockito;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class BookaTester extends AbstractModule implements Module {

    public final RouterTester router;
    public final Injector injector;
    public final DefaultEventBus eventBus;
    public final RestManager restManager;

    public BookaTester() {
	eventBus = new DefaultEventBus();
	router = new RouterTester(eventBus);
	restManager = Mockito.mock(RestManager.class);
	injector = Guice.createInjector(this);
    }

    public <T> T get(Class<T> modelClass) {
	return injector.getInstance(modelClass);
    }

    private void addDisplay(Class<DocumentItemDisplay> type) {
	bind(type).toProvider(DisplayProvider.get(type));
    }

    @Override
    protected void configure() {
	bind(Router.class).toInstance(router);
	bind(EventBus.class).toInstance(eventBus);
	bind(RestManager.class).toInstance(restManager);
	addDisplay(DocumentItemDisplay.class);
    }

}
