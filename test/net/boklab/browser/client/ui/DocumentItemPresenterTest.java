package net.boklab.browser.client.ui;

import static org.junit.Assert.assertEquals;
import net.boklab.core.client.model.Bok;
import net.boklab.module.archives.client.archive.browser.ArchiveItemDisplay;
import net.boklab.module.archives.client.archive.browser.ArchiveItemPresenter;
import net.boklab.testing.Boky;
import net.boklab.testing.BookaTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class DocumentItemPresenterTest {

    private ArchiveItemPresenter presenter;
    private ArchiveItemDisplay display;
    private RouterTester router;

    @Before
    public void setup() {
	final BookaTester tester = new BookaTester();
	presenter = tester.get(ArchiveItemPresenter.class);
	display = presenter.getDisplay();
	router = tester.router;
    }

    @Test
    public void shouldFireItemClickedEvent() {
	final Bok doc = Boky.bok("Document");
	presenter.setDocument(doc);
	display.getClickeable().fireEvent(null);
	assertEquals(new Place("documents", doc.getId()), router.getLastRequest());
    }

    @Test
    public void shouldSetDocumentProperties() {
	final Bok document = Boky.document();
	presenter.setDocument(document);
	assertEquals(document.getTitle(), display.getDocumentTitle().getText());
    }
}
