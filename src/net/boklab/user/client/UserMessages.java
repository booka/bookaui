package net.boklab.user.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface UserMessages extends Messages {

    @DefaultMessage("tuscosillas")
    String accountResource();

    @DefaultMessage("Visitante")
    String anonymous();

    @DefaultMessage("entrar")
    String loginResource();

    @DefaultMessage("Entrar en Booka")
    String placeLogin();

}
