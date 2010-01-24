package net.boklab.core.client;

public interface BokSearchResults {

    Bok getBok();

    Bok getChildren(int index);

    int getChildrenSize();
}
