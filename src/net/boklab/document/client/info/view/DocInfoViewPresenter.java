package net.boklab.document.client.info.view;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocInfoViewPresenter extends AbstractPresenter<DocInfoViewDisplay> {

    @Inject
    public DocInfoViewPresenter(Provider<DocInfoViewDisplay> provider) {
	super(provider);
    }

    public void setDocument(Document document) {
	DocInfoViewDisplay display = getDisplay();
	display.getDocumentTitle().setText(document.getTitle());
	display.getDocumentDescription().setText(document.getDescription());
    }

}
