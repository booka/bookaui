package net.boklab.project.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface ProjectMessages extends Messages {

    @DefaultMessage("Trabajando...")
    String loadingSite();

    @DefaultMessage("Abriendo investigación...")
    String openingProject();

    @DefaultMessage("Estás en la investigación ''{0}''")
    String projectOpened(String title);

    @DefaultMessage("Investigaciones y convocatorias.")
    String siteLoaded();

}
