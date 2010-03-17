package net.boklab.module.explore.client.browser;

import net.boklab.core.client.ui.action.Action;
import net.boklab.module.explore.client.pointer.PointerPresenter;

public interface IndiceBrowserAction extends Action {

    void setPresenter(IndiceBrowserPresenter indiceBrowserPresenter);

    void setSelected(PointerPresenter selected);

}
