package net.boklab.document.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface DocumentMessages extends Messages {

    @DefaultMessage("A–adir texto")
    String createHtmlAction();

    @DefaultMessage("Entra para poder editar")
    String loginToEdit();

}
