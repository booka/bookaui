package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CallContentHandler extends InfoContentHandler {

    @Inject
    public CallContentHandler(final Provider<InfoEditorPresenter> provider) {
	super(Bok.CALL, provider);
    }

}
