package net.boklab.booka.client;

import net.boklab.core.client.BokCoreGinjector;
import net.boklab.module.archives.client.ArchivesGinjector;
import net.boklab.module.calendar.client.BokCalendarGinjector;
import net.boklab.module.explore.client.ExploreGinjector;
import net.boklab.module.forum.client.ForumGinjector;
import net.boklab.places.client.BokPlacesGinjector;
import net.boklab.tools.client.BokToolsGinjector;
import net.boklab.user.client.BokUserGinjector;
import net.boklab.workspace.client.BokWorkspaceGinjector;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(BookaModule.class)
public interface BookaGinjector extends ArchivesGinjector, BokWorkspaceGinjector, BokToolsGinjector,
	BokCoreGinjector, BokCalendarGinjector, BokUserGinjector, BokPlacesGinjector,
	ExploreGinjector, ForumGinjector {
    BookaAppPresenter getBookaAppPresenter();

}
