package net.boklab.core.client.model;

public interface BokResponse {

    BokJSO getBok();

    BokJSO getChildren(int index);

    int getChildrenSize();

    BokJSO getUpdated(int index);

    int getUpdatedSize();
}
