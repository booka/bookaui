package net.boklab.project.client;

import net.boklab.project.client.action.Projects;
import net.boklab.tools.client.router.Router;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectRouter {
    @Inject
    public ProjectRouter(Router router, final Projects manager) {
	Log.debug("Init project router");
    }
}
