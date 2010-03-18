package net.boklab.module.archives.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface ArchivesMessages extends Messages {
    @DefaultMessage("Añadir un documento al archivo")
    String actionNewDocument();

    @DefaultMessage("Artículos y proyectos")
    String browserTitle();

    @DefaultMessage("Abriendo archivos de ''{0}''...")
    String openArchive(String title);

    @DefaultMessage("Abriendo archivos...")
    String openArchiveUntitled();

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

    @DefaultMessage("Abriendo investigaciones...")
    String openSiteUntitled();

    @DefaultMessage("Archivos")
    String placeDescriptionArchives();

    @DefaultMessage("Guardando...")
    String update();
}
