package net.boklab.indices.client.browser;

import net.boklab.core.client.ui.action.Action;
import net.boklab.core.client.ui.action.ActionDisplay;
import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.ui.HasText;

public interface BrowserDisplay extends Display {

    ActionDisplay addAction(Action<?> action);

    void addItem(final Display display);

    void clearList();

    HasText getBrowserTitle();
}
