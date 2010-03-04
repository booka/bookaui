package net.boklab.core.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.model.Bok;

public interface BokManager {

    void addCreatedHandler(BokCreatedHandler handler);

    void addOpenedHandler(BokOpenedHandler handler);

    void addOpenHandler(OpenBokHandler handler);

    void addRetrievedHandler(BokRetrievedHandler handler);

    void addUpdatedHandler(BokUpdatedHandler handler);

    void open(Bok bok, boolean forceOpen);

    void open(String bokId, String knownTitle, boolean forceOpen);
}
