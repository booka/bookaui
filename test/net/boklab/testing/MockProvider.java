package net.boklab.testing;

import org.mockito.Mockito;

import com.google.inject.Provider;

public class MockProvider<T> implements Provider<T> {

    public static <T> MockProvider<T> get(Class<T> type) {
	return new MockProvider<T>(type);
    }

    private final Class<T> type;
    private T instance;

    public MockProvider(Class<T> type) {
	this.type = type;
    }

    @Override
    public T get() {
	if (this.instance == null)
	    this.instance = Mockito.mock(type);
	return this.instance;
    }
}
