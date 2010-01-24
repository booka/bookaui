package net.boklab.document.client;

import static org.mockito.Mockito.verify;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.testing.BookaTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class DocumentRouterTest {
    private BookaTester test;
    private DocumentManager manager;

    @Before
    public void setup() {
	test = new BookaTester();
	manager = test.get(DocumentManager.class);
	test.get(DocumentRouter.class);
    }

    @Test
    public void shouldLoadDocumentClips() {
	test.router.fireRequest(new Place("documents", "1"));
	verify(manager).getDocumentClips("1");
    }

}
