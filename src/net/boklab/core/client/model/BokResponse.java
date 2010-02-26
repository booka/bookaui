package net.boklab.core.client.model;


public interface BokResponse {

    Bok getBok();

    Bok getChildren(int index);

    int getChildrenSize();
}
