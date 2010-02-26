package net.boklab.document.client;

import net.boklab.document.client.clip.ClipDisplay;
import net.boklab.document.client.clip.ClipWidget;
import net.boklab.document.client.clip.action.ClipActionsDisplay;
import net.boklab.document.client.clip.action.ClipActionsWidget;
import net.boklab.document.client.content.ContentTypeListDisplay;
import net.boklab.document.client.content.ContentTypeListPresenter;
import net.boklab.document.client.content.ContentTypeListWidget;
import net.boklab.document.client.content.ContentTypeRegistry;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.content.editor.ClipEditorWidget;
import net.boklab.document.client.content.html.HtmlContentType;
import net.boklab.document.client.content.html.HtmlEditorDisplay;
import net.boklab.document.client.content.html.HtmlEditorWidget;
import net.boklab.document.client.doc.DocumentDisplay;
import net.boklab.document.client.doc.DocumentWidget;
import net.boklab.document.client.info.DocInfoDisplay;
import net.boklab.document.client.info.DocInfoWidget;
import net.boklab.document.client.info.edit.DocInfoEditorDisplay;
import net.boklab.document.client.info.edit.DocInfoEditorWidget;
import net.boklab.document.client.info.view.DocInfoViewerDisplay;
import net.boklab.document.client.info.view.DocInfoViewerWidget;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.manager.DocumentsBridge;
import net.boklab.document.client.manager.DocumentsWorker;
import net.boklab.document.client.slot.SlotDisplay;
import net.boklab.document.client.slot.SlotWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DocumentsModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(Documents.class).to(DocumentsBridge.class).in(Singleton.class);
	bind(DocumentsWorker.class).asEagerSingleton();
	bind(DocumentsRouter.class).asEagerSingleton();
	bind(DocumentDisplay.class).to(DocumentWidget.class).in(Singleton.class);
	bind(ClipDisplay.class).to(ClipWidget.class);
	bind(DocInfoEditorDisplay.class).to(DocInfoEditorWidget.class);
	bind(DocInfoDisplay.class).to(DocInfoWidget.class);
	bind(DocInfoViewerDisplay.class).to(DocInfoViewerWidget.class);
	bind(SlotDisplay.class).to(SlotWidget.class);
	bind(ContentTypeListDisplay.class).to(ContentTypeListWidget.class);
	bind(ContentTypeListPresenter.class).in(Singleton.class);
	bind(HtmlEditorDisplay.class).to(HtmlEditorWidget.class);
	bind(ContentTypeRegistry.class).in(Singleton.class);
	bind(HtmlContentType.class).in(Singleton.class);
	bind(ContentTypeRegistrator.class).asEagerSingleton();
	bind(ClipEditorDisplay.class).to(ClipEditorWidget.class);
	bind(ClipActionRegistrator.class).asEagerSingleton();

	bind(ClipActionsDisplay.class).to(ClipActionsWidget.class);
    }

}
