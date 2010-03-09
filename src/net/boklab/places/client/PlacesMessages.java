package net.boklab.places.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface PlacesMessages extends Messages {

    @DefaultMessage("Investigaciones y convocatorias")
    String entrancePlaceDescription();

    @DefaultMessage("Abriendo convocatoria...")
    String loadingCall();

    @DefaultMessage("Abriendo documento ''{0}''...")
    String loadingDocument(String title);

    @DefaultMessage("Abriendo documento...")
    String loadingDocumentUnknown();

    @DefaultMessage("Abriendo investigación...")
    String loadingProject();

    @DefaultMessage("Abriendo investigaciones...")
    String loadingSite();

    @DefaultMessage("Investigaciones y convocatorias")
    String placeDescriptionSite();

    @DefaultMessage("Investigación ''{0}''")
    String projectName(String projectTitle);

    @DefaultMessage("Estás en la investigación ''{0}''")
    String projectOpened(String title);

    @DefaultMessage("archivo")
    String resourceArchives();

    @DefaultMessage("explorar")
    String resourceBrowse();

    @DefaultMessage("convocatoria")
    String resourceCall();

    @DefaultMessage("entrada")
    String resourceEntrance();

    @DefaultMessage("investigaciones")
    String resourceProjects();

    @DefaultMessage("Abriendo investigación...")
    String unknownProjectName();

}
