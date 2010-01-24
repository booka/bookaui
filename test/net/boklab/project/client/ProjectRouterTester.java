package net.boklab.project.client;

import static org.mockito.Mockito.verify;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.testing.BookaTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class ProjectRouterTester {
    private ProjectManager manager;
    private RouterTester router;

    @Before
    public void setup() {
	BookaTester test = new BookaTester();
	router = test.router;
	manager = test.get(ProjectManager.class);
	test.get(ProjectRouter.class);
    }

    @Test
    public void shouldLoadProjectDocuments() {
	router.fireRequest(new Place("archives", "docId"));
	verify(manager).getProjectDocuments("docId");
    }

    @Test
    public void shouldLoadProjectList() {
	router.fireRequest(new Place("entrance"));
	verify(manager).getProjectList();
    }

}
