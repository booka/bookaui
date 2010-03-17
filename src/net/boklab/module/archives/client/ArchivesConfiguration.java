package net.boklab.module.archives.client;

import net.boklab.core.client.module.ModuleConfig;
import net.boklab.module.archives.client.places.ArchivesLocation;
import net.boklab.module.archives.client.places.DocumentLocation;
import net.boklab.module.entrance.client.call.CallManager;
import net.boklab.module.entrance.client.project.ProjectManager;
import net.boklab.module.entrance.client.site.SiteManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchivesConfiguration {

    @Inject
    public ArchivesConfiguration(final ModuleConfig config, final SiteManager sites,
	    final ProjectManager projects, final CallManager calls,
	    final ArchivesLocation archives, final DocumentLocation documents,
	    final ArchivesNavigator archivesNavigator) {
	config.addNavigationHandler(NavigationDisplay.ARCHIVES, archivesNavigator);
	config.addLocation(archives);
	config.addLocation(documents);

    }
}
