package net.boklab.browser.client.ui;

import static org.junit.Assert.assertEquals;
import net.boklab.core.client.SimpleBok;
import net.boklab.document.client.model.Document;
import net.boklab.testing.BookaTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class DocumentItemPresenterTest {

    private DocumentItemPresenter presenter;
    private DocumentItemDisplay display;
    private RouterTester router;

    @Before
    public void setup() {
	BookaTester tester = new BookaTester();
	presenter = tester.get(DocumentItemPresenter.class);
	display = presenter.getDisplay();
	router = tester.router;
    }

    @Test
    public void shouldFireItemClickedEvent() {
	presenter.setDocument(SimpleBok.newDocument("docID"));
	display.getClickeable().fireEvent(null);
	assertEquals(new Place("documents", "docID"), router.getLastRequest());
    }

    @Test
    public void shouldSetDocumentProperties() {
	Document document = SimpleBok.newDocument("1");
	presenter.setDocument(document);
	assertEquals(document.getTitle(), display.getDocumentTitle().getText());
    }
}
