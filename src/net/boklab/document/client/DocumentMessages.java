package net.boklab.document.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface DocumentMessages extends Messages {

    @DefaultMessage("Añadir texto")
    String createHtmlAction();

    @DefaultMessage("Añadir un enlace")
    String createLinkAction();

    @DefaultMessage("''{0}'' listo.")
    String documentOpened(String title);

    @DefaultMessage("Entra para poder editar")
    String loginToEdit();

    @DefaultMessage("Abriendo documento....")
    String openingDocument();

}
