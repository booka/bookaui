package net.boklab.site.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface SiteMessages extends Messages {

    @DefaultMessage("Abriendo convocatoria ''{0}''...")
    String openCall(String title);

    @DefaultMessage("Abriendo convocatoria...")
    String openCallUntitled();

    @DefaultMessage("Abriendo investigación ''{0}''")
    String openProject(String title);

    @DefaultMessage("Abriendo investigación...")
    String openProjectUntitled();

    @DefaultMessage("Abriendo ''{0}''...")
    String openSite(String title);

    @DefaultMessage("Abriendo entrada...")
    String openSiteUntitled();

}
