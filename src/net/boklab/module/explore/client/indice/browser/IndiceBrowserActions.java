package net.boklab.module.explore.client.indice.browser;

import net.boklab.core.client.ui.browser.BrowserActions;
import net.boklab.module.explore.client.indice.browser.actions.AddPointerAction;
import net.boklab.module.explore.client.indice.browser.actions.LoginToEditIndiceAction;
import net.boklab.module.explore.client.indice.browser.actions.RemovePointerAction;
import net.boklab.module.explore.client.pointer.PointerPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndiceBrowserActions extends BrowserActions<IndiceBrowserPresenter, PointerPresenter> {

    @Inject
    public IndiceBrowserActions(final LoginToEditIndiceAction loginAction,
	    final AddPointerAction addPointerAction, final RemovePointerAction removePointerAction) {
	addAction(loginAction);
	addAction(addPointerAction);
	addAction(removePointerAction);
    }
}
