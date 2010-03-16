package net.boklab.indices.client.browser;

import net.boklab.indices.client.browser.actions.AddPointerAction;
import net.boklab.indices.client.browser.actions.LoginToEditIndiceAction;
import net.boklab.indices.client.browser.actions.RemovePointerAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndiceBrowserActionRegistrar {

    @Inject
    public IndiceBrowserActionRegistrar(final IndiceBrowserPresenter indices,
	    final LoginToEditIndiceAction loginAction, final AddPointerAction addPointerAction,
	    final RemovePointerAction removePointerAction) {
	indices.addAction(loginAction);
	indices.addAction(addPointerAction);
	indices.addAction(removePointerAction);
    }
}
