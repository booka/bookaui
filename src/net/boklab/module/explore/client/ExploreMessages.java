package net.boklab.module.explore.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface ExploreMessages extends Messages {

    @DefaultMessage("Añadir un elemento índice")
    String actionAdd();

    @DefaultMessage("Entra para modificar este índice")
    String actionLoginToEditIndice();

    @DefaultMessage("Borrar el elemento seleccionado del índice")
    String actionRemovePointer();

    @DefaultMessage("Abriendo booka...")
    String openIndiceUnknown();

    @DefaultMessage("Guardando...")
    String update();

}
