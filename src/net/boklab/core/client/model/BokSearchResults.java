package net.boklab.core.client.model;

public interface BokSearchResults {

    Bok getBok();

    Bok getChildren(int index);

    int getChildrenSize();
}
