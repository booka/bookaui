package net.boklab.core.client.model;

public interface Bok {

    String getBody();

    String getBokType();

    String getDescription();

    String getId();

    String getParentId();

    String getTitle();

    String getUserId();

    String getUserName();

    void setBody(String body);

    void setBokType(String type);

    void setDescription(String text);

    void setParentId(String id);

    void setTitle(String title);

    void setUserId(String id);
}
