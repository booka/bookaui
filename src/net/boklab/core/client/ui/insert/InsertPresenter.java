package net.boklab.core.client.ui.insert;

import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Esto será para lo de insertar. Hover panels, supongo
 * 
 * @author dani
 * 
 */
@Singleton
public class InsertPresenter implements Presenter<InsertDisplay> {

    private final InsertDisplay display;

    @Inject
    public InsertPresenter(final InsertDisplay display) {
	this.display = display;
    }

    @Override
    public void bind() {
    }

    @Override
    public InsertDisplay getDisplay() {
	return display;
    }
}
