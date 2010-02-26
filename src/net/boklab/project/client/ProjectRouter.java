package net.boklab.project.client;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.router.Router;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectRouter {
    @Inject
    public ProjectRouter(final Router router, final ProjectManager manager) {
	Log.debug("Init project router");
    }
}
