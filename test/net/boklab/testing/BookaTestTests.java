package net.boklab.testing;

import static org.junit.Assert.assertNotNull;
import net.boklab.document.client.manager.DefaultDocumentManager;

import org.junit.Before;
import org.junit.Test;

public class BookaTestTests {
    private BookaTester test;

    @Before
    public void setup() {
	test = new BookaTester();
    }

    @Test
    public void shouldCreateDocumentManager() {
	DefaultDocumentManager manager = test.get(DefaultDocumentManager.class);
	assertNotNull(manager);
    }

}
