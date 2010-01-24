package net.boklab.document.client.ui;

import net.boklab.testing.BookaTester;

import org.junit.Before;
import org.junit.Test;

public class DocumentPresenterTests {
    private DocumentPresenter presenter;

    @Before
    public void setup() {
	BookaTester tester = new BookaTester();
	presenter = tester.get(DocumentPresenter.class);
    }

    @Test
    public void shouldShowDocument() {
    }
}
