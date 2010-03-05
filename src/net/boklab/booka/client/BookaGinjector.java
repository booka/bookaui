package net.boklab.booka.client;

import net.boklab.browser.client.BrowserGinjector;
import net.boklab.calendar.client.BokCalendarGinjector;
import net.boklab.core.client.BokCoreGinjector;
import net.boklab.indices.client.BokIndicesGinjector;
import net.boklab.places.client.BokPlacesGinjector;
import net.boklab.tools.client.BokToolsGinjector;
import net.boklab.user.client.BokUserGinjector;
import net.boklab.workspace.client.BokWorkspaceGinjector;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(BookaModule.class)
public interface BookaGinjector extends BrowserGinjector, BokWorkspaceGinjector, BokToolsGinjector, BokCoreGinjector,
	BokCalendarGinjector, BokUserGinjector, BokPlacesGinjector, BokIndicesGinjector {
    BookaAppPresenter getBookaAppPresenter();

}
