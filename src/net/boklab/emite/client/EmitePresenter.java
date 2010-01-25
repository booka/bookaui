package net.boklab.emite.client;

import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EmitePresenter extends AbstractPresenter<EmiteDisplay> {

    @Inject
    public EmitePresenter(Provider<EmiteDisplay> provider) {
	super(provider);
    }
}
