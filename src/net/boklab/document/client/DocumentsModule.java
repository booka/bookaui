package net.boklab.document.client;

import net.boklab.document.client.actions.ActionsRegistrator;
import net.boklab.document.client.clip.ClipDisplay;
import net.boklab.document.client.clip.ClipWidget;
import net.boklab.document.client.clip.action.ClipActionsDisplay;
import net.boklab.document.client.clip.action.ClipActionsWidget;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.clip.editor.ClipEditorWidget;
import net.boklab.document.client.content.ContentTypeRegistry;
import net.boklab.document.client.content.debug.DebugEditorDisplay;
import net.boklab.document.client.content.debug.DebugEditorWidget;
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
import net.boklab.document.client.manager.ClipsPersistence;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.manager.DocumentsBridge;
import net.boklab.document.client.manager.DocumentsPersistence;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DocumentsModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(DocumentsPersistence.class).asEagerSingleton();
	bind(ClipsPersistence.class).asEagerSingleton();
	bind(DocumentsRouter.class).asEagerSingleton();

	bind(ContentTypeRegistrator.class).asEagerSingleton();
	bind(ActionsRegistrator.class).asEagerSingleton();
	bind(ContentTypeRegistry.class).in(Singleton.class);

	bind(Documents.class).to(DocumentsBridge.class).in(Singleton.class);

	bind(DocumentDisplay.class).to(DocumentWidget.class).in(Singleton.class);
	bind(ClipDisplay.class).to(ClipWidget.class);
	bind(DocInfoEditorDisplay.class).to(DocInfoEditorWidget.class);
	bind(DocInfoDisplay.class).to(DocInfoWidget.class);
	bind(DocInfoViewerDisplay.class).to(DocInfoViewerWidget.class);
	bind(ClipActionsDisplay.class).to(ClipActionsWidget.class);

	bind(HtmlEditorDisplay.class).to(HtmlEditorWidget.class);
	bind(ClipEditorDisplay.class).to(ClipEditorWidget.class);
	bind(DebugEditorDisplay.class).to(DebugEditorWidget.class);
    }

}
