package net.boklab.document.client.ui;

import static org.mockito.Mockito.verify;
import net.boklab.document.client.doc.DocumentDisplay;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.testing.Boky;
import net.boklab.testing.BookaTester;
import net.boklab.tools.client.eventbus.EventBus;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class DocumentPresenterTests {

    @Inject
    EventBus eventBus;
    @Inject
    DocumentPresenter presenter;
    @Inject
    DocumentDisplay display;

    @Before
    public void setup() {
	new BookaTester(this);
    }

    @Test
    public void shouldClearContentsWhenDocumentOpened() {
	eventBus.fireEvent(new DocumentOpenedEvent(Boky.document()));
	verify(display).clear();
    }
}
