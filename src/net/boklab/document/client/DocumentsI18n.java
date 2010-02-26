package net.boklab.document.client;

public class DocumentsI18n {

    private static DocumentMessages messages;

    public static DocumentMessages i18n() {
	return messages;
    }

    public static void set(final DocumentMessages m) {
	DocumentsI18n.messages = m;
    }

}
