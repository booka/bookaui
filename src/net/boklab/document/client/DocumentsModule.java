package net.boklab.document.client;

import net.boklab.document.client.actions.ActionsRegistrator;
import net.boklab.document.client.bok.BokDisplay;
import net.boklab.document.client.bok.BokWidget;
import net.boklab.document.client.bok.action.BokActionsDisplay;
import net.boklab.document.client.bok.action.BokActionsWidget;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.bok.editor.BokEditorWidget;
import net.boklab.document.client.bok.insert.SelectContentDisplay;
import net.boklab.document.client.bok.insert.SelectContentWidget;
import net.boklab.document.client.content.ContentHandlerRegistry;
import net.boklab.document.client.content.ContentRegistrator;
import net.boklab.document.client.content.debug.DebugEditorDisplay;
import net.boklab.document.client.content.debug.DebugEditorWidget;
import net.boklab.document.client.content.html.HtmlEditorDisplay;
import net.boklab.document.client.content.html.HtmlEditorWidget;
import net.boklab.document.client.content.info.InfoEditorDisplay;
import net.boklab.document.client.content.info.InfoEditorWidget;
import net.boklab.document.client.content.link.LinkEditorDisplay;
import net.boklab.document.client.content.link.LinkEditorWidget;
import net.boklab.document.client.doc.DocumentDisplay;
import net.boklab.document.client.doc.DocumentWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DocumentsModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(DocumentManager.class).asEagerSingleton();

	bind(ContentRegistrator.class).asEagerSingleton();
	bind(ActionsRegistrator.class).asEagerSingleton();
	bind(ContentHandlerRegistry.class).in(Singleton.class);

	bind(DocumentDisplay.class).to(DocumentWidget.class).in(Singleton.class);
	bind(BokDisplay.class).to(BokWidget.class);
	bind(InfoEditorDisplay.class).to(InfoEditorWidget.class);
	bind(BokActionsDisplay.class).to(BokActionsWidget.class);
	bind(SelectContentDisplay.class).to(SelectContentWidget.class);

	bind(HtmlEditorDisplay.class).to(HtmlEditorWidget.class);
	bind(BokEditorDisplay.class).to(BokEditorWidget.class);
	bind(DebugEditorDisplay.class).to(DebugEditorWidget.class);
	bind(LinkEditorDisplay.class).to(LinkEditorWidget.class);
    }

}
