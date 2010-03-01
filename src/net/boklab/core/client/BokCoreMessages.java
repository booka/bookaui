package net.boklab.core.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface BokCoreMessages extends Messages {

    @DefaultMessage("Cancelar")
    String cancelAction();

    @DefaultMessage("Cerrar")
    String closeAction();

    @DefaultMessage("Editar")
    String editAction();

    @DefaultMessage("Guardar")
    String saveAction();
}
