package net.boklab.document.client.content.info;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentContentHandler extends InfoContentHandler {

    @Inject
    public DocumentContentHandler(final Provider<InfoEditorPresenter> provider) {
	super("Document", provider);
    }

}
