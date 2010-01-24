package net.boklab.document.client;

import net.boklab.document.client.manager.Documents;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;

public class DocumentRouter {

    @Inject
    public DocumentRouter(Router router, final Documents manager) {
    }
}
