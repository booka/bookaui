package net.boklab.document.client.info;

import net.boklab.document.client.info.edit.DocInfoEditorPresenter;
import net.boklab.document.client.info.view.DocInfoViewPresenter;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocInfoPresenter extends AbstractPresenter<DocInfoDisplay> {

    private final DocInfoViewPresenter infoView;
    private final DocInfoEditorPresenter infoEditor;

    @Inject
    public DocInfoPresenter(DocInfoViewPresenter infoView, DocInfoEditorPresenter infoEditor,
	    Provider<DocInfoDisplay> provider) {
	super(provider);
	this.infoView = infoView;
	this.infoEditor = infoEditor;
    }

    public void setDocument(Document document) {
	infoView.setDocument(document);
	infoEditor.setDocument(document);
    }

}
