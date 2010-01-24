package net.boklab.testing;

import net.boklab.testing.display.Dimock;
import net.boklab.tools.client.mvp.Display;

import com.google.inject.Provider;

public class DisplayProvider<T> implements Provider<T> {

    public static <T extends Display> DisplayProvider<T> get(Class<T> type) {
	return new DisplayProvider<T>(type);
    }

    private final Class<T> type;

    public DisplayProvider(Class<T> type) {
	this.type = type;
    }

    @Override
    public T get() {
	return Dimock.mock(type);
    }

}
