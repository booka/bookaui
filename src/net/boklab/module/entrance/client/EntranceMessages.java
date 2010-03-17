package net.boklab.module.entrance.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface EntranceMessages extends Messages {

    @DefaultMessage("Investigaciones y convocatorias")
    String entrancePlaceDescription();

    @DefaultMessage("Abriendo convocatoria...")
    String loadingCall();

    @DefaultMessage("Abriendo documento ''{0}''...")
    String loadingDocument(String title);

    @DefaultMessage("Abriendo documento...")
    String loadingDocumentUnknown();

    @DefaultMessage("Archivos de ''{0}''")
    String locationArchives(String title);

    @DefaultMessage("Convocatoria para ''{0}''")
    String locationCall(String title);

    @DefaultMessage("Explorando ''{0}''")
    String locationIndice(String title);

    @DefaultMessage("{0}")
    String locationProject(String title);

    @DefaultMessage("Investigaciones y convocatorias")
    String locationSite();

    @DefaultMessage("investigaciones")
    String NOresourceProjects();

    @DefaultMessage("Investigación ''{0}''")
    String projectName(String projectTitle);

    @DefaultMessage("Estás en la investigación ''{0}''")
    String projectOpened(String title);

    @DefaultMessage("archivo")
    String resourceArchive();

    @DefaultMessage("archivos")
    String resourceArchives();

    @DefaultMessage("explorar")
    String resourceBrowse();

    @DefaultMessage("convocatoria")
    String resourceCalls();

    @DefaultMessage("documentos")
    String resourceDocuments();

    @DefaultMessage("entrada")
    String resourceEntrance();

    @DefaultMessage("Abriendo investigación...")
    String unknownProjectName();

}
