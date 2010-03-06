package net.boklab.document.client.bok.editor;

import net.boklab.document.client.content.ContentEditor;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;

import com.google.inject.Provider;

public abstract class AbstractEditor<T extends Display> extends AbstractPresenter<T> implements
	ContentEditor<T> {

    public AbstractEditor(final Provider<T> provider) {
	super(provider);
    }

}
