package net.boklab.indices.client.browser.actions;

import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.indices.client.I18nIndices;
import net.boklab.indices.client.browser.IndiceBrowserAction;
import net.boklab.indices.client.browser.IndiceBrowserPresenter;
import net.boklab.indices.client.pointer.PointerPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RemovePointerAction extends AbstractAction implements IndiceBrowserAction {

    private IndiceBrowserPresenter presenter;

    @Inject
    public RemovePointerAction(final Sessions sessions) {
	super(I18nIndices.t.actionRemovePointer(), Icons.get(BokIcon.none), sessions);
    }

    @Override
    public void execute() {
    }

    @Override
    public void setPresenter(final IndiceBrowserPresenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void setSelected(final PointerPresenter selected) {
	changeVisibility();
    }

    private void changeVisibility() {
	setVisible(sessions.isLoggedIn() && presenter != null && presenter.hasSelected());
    }

    @Override
    protected void onSessionChanged() {
	changeVisibility();
    }
}
