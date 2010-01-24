package net.boklab.core.client;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.model.Document;

public class SimpleBok implements Bok {

    public static Document newDocument(String id) {
	return new Document(new SimpleBok(id, Document.TYPE));
    }
    private String userId;
    private String title;
    private String parentId;
    private String description;
    private String bokType;
    private String body;
    private String userName;

    private final String id;

    public SimpleBok(String id, String bokType) {
	this.id = id;
	this.bokType = bokType;
    }

    @Override
    public String getBody() {
	return body;
    }

    @Override
    public String getBokType() {
	return bokType;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public String getParentId() {
	return parentId;
    }

    @Override
    public String getTitle() {
	return title;
    }

    @Override
    public String getUserId() {
	return userId;
    }

    @Override
    public String getUserName() {
	return userName;
    }

    @Override
    public void setBody(String body) {
	this.body = body;
    }

    @Override
    public void setBokType(String bokType) {
	this.bokType = bokType;
    }

    @Override
    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public void setParentId(String parentId) {
	this.parentId = parentId;
    }

    @Override
    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public void setUserId(String userId) {
	this.userId = userId;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

}
