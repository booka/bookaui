package net.boklab.core.client.model;

import java.util.ArrayList;

public interface Bok extends BokDTO {

    ArrayList<Bok> getChildren();

    Bok getParent();

    DelegatedBok newChild(String type, String title, String userId, int position);

    void setParent(Bok bok);
}
