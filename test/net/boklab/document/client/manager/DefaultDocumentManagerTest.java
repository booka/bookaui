package net.boklab.document.client.manager;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import net.boklab.testing.BookaTester;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.rest.Params;
import net.boklab.tools.client.rest.RestCallback;

import org.junit.Before;
import org.junit.Test;

public class DefaultDocumentManagerTest {
    private DefaultDocumentManager manager;
    private BookaTester test;

    @Before
    public void setup() {
	test = new BookaTester();
	manager = test.get(DefaultDocumentManager.class);
    }

    @Test
    public void shouldLoadDocument() {
	test.router.fireRequest(new Place("documents", "1"));
	verify(test.restManager).getList(anyString(), anyString(), (Params) anyObject(),
		(RestCallback) anyObject());
    }

}
