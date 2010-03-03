package net.boklab.site.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface SiteMessages extends Messages {

    @DefaultMessage("Abriendo documento...")
    String loadingDocument();

    @DefaultMessage("Abriendo investigaciones...")
    String loadingSite();

    @DefaultMessage("Abriendo investigación...")
    String loadingProject();

    @DefaultMessage("Investigación ''{0}''")
    String projectName(String projectTitle);

    @DefaultMessage("Estás en la investigación ''{0}''")
    String projectOpened(String title);

    @DefaultMessage("investigaciones")
    String projectsResourceName();

    @DefaultMessage("Abriendo investigación...")
    String unknownProjectName();

}
