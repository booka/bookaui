package net.boklab.browser.client.ui;

import static org.junit.Assert.assertEquals;
import net.boklab.document.client.model.Document;
import net.boklab.testing.Boky;
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
	Document doc = Boky.document();
	presenter.setDocument(doc);
	display.getClickeable().fireEvent(null);
	assertEquals(new Place("documents", doc.getId()), router.getLastRequest());
    }

    @Test
    public void shouldSetDocumentProperties() {
	Document document = Boky.document();
	presenter.setDocument(document);
	assertEquals(document.getTitle(), display.getDocumentTitle().getText());
    }
}
