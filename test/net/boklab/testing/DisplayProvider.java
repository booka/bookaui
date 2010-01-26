package net.boklab.testing;

import static org.mockito.Mockito.when;
import net.boklab.testing.display.DisplayStubFactory;
import net.boklab.testing.display.ReturnsSingletonMocks;
import net.boklab.tools.client.mvp.Display;

import org.mockito.Mockito;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;

public class DisplayProvider<T extends Display> implements Provider<T> {
    public static final DisplayStubFactory factory = DisplayStubFactory.instance;

    public static <T extends Display> DisplayProvider<T> get(Class<T> type) {
	return new DisplayProvider<T>(type);
    }

    private final Class<T> type;

    public DisplayProvider(Class<T> type) {
	this.type = type;
    }

    @Override
    public T get() {
	Widget widget = Mockito.mock(Widget.class);
	T mock = Mockito.mock(type, new ReturnsSingletonMocks(factory));
	when(mock.asWidget()).thenReturn(widget);
	return mock;
    }
}
