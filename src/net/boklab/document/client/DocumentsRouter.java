package net.boklab.document.client;

import net.boklab.document.client.manager.Documents;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;

public class DocumentsRouter {

    @Inject
    public DocumentsRouter(Router router, final Documents manager) {
    }
}
