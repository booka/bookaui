package net.boklab.testing.display;

import org.mockito.Mockito;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Provider;

public class Dimock {

    public static class DisplayFactory {

	public static DisplayFactory instance = new DisplayFactory();

	private final HashMap<Class<?>, Provider<?>> providers;

	private DisplayFactory() {
	    providers = new HashMap<Class<?>, Provider<?>>();
	    providers.put(HasHTML.class, new Provider<HasHTML>() {
		@Override
		public HasHTML get() {
		    return new HasHTMLStub();
		}
	    });
	    providers.put(HasText.class, new Provider<HasText>() {
		@Override
		public HasText get() {
		    return new HasTextStub();
		}
	    });
	    providers.put(HasClickHandlers.class, new Provider<HasClickHandlers>() {
		@Override
		public HasClickHandlers get() {
		    return new HasClickHandlersStub();
		}
	    });
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> mockType) {
	    Provider<?> provider = providers.get(mockType);
	    if (provider == null) {
		throw new RuntimeException("No provider for " + mockType.getSimpleName());
	    }
	    return (T) provider.get();
	}
    }

    public static final DisplayFactory factory = DisplayFactory.instance;

    public static <T> T mock(Class<T> classToMock) {
	return Mockito.mock(classToMock, new ReturnsSingletonMocks(factory));
    }

}
