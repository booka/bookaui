package net.boklab.core.client.model;

import java.util.ArrayList;

public interface Bok extends BokDTO {

    public static final String CALL = "Call";
    public static final String CLIP = "Clip";
    public static final String DOCUMENT = "Document";
    public static final String PROJECT = "Project";
    public static final String SITE = "Site";

    ArrayList<Bok> getChildren();

    Bok getFirstChild(String bokType);

    Bok getParent();

    DelegatedBok newChild(String type, String title, String userId, int position);

    void setParent(Bok bok);
}
