package net.boklab.core.client.model;

public interface Bok {

    String getBody();

    String getBokType();

    String getContentType();

    String getDescription();

    String getId();

    String getParentId();

    Integer getPosition();

    String getTitle();

    String getUpdatedAt();

    String getUserId();

    String getUserName();

    void setBody(String body);

    void setBokType(String type);

    void setContentType(String contentType);

    void setDescription(String text);

    void setParentId(String id);

    void setPosition(int position);

    void setTitle(String title);

    void setUserId(String id);
}
