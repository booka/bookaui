package net.boklab.document.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface DocumentMessages extends Messages {

    @DefaultMessage("Archivos")
    String browserTitle();

    @DefaultMessage("Añadir texto")
    String createHtmlAction();

    @DefaultMessage("Añadir un enlace")
    String createLinkAction();

    @DefaultMessage("''{0}'' listo.")
    String documentOpened(String title);

    @DefaultMessage("Entra para poder editar")
    String loginToEdit();

    @DefaultMessage("Abriendo archivos de ''{0}''...")
    String openArchive(String title);

    @DefaultMessage("Abriendo archivos...")
    String openArchiveUntitled();

    @DefaultMessage("Cargando documento...")
    String openClip();

    @DefaultMessage("Abriendo documento ''{0}''...")
    String openDocument(String title);

    @DefaultMessage("Abriendo documento....")
    String openDocumentUntitled();

    @DefaultMessage("Guardando...")
    String update();

}
