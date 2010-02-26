package net.boklab.core.client;

import net.boklab.core.client.model.Bok;

public class SimpleBok implements Bok {

    private String userId;
    private String title;
    private String parentId;
    private String description;
    private String bokType;
    private String body;
    private String userName;

    private final String id;
    private String contentType;
    private int position;

    public SimpleBok(final String id, final String bokType) {
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
    public String getContentType() {
	return contentType;
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
    public int getPosition() {
	return position;
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
    public void setBody(final String body) {
	this.body = body;
    }

    @Override
    public void setBokType(final String bokType) {
	this.bokType = bokType;
    }

    @Override
    public void setContentType(final String contentType) {
	this.contentType = contentType;
    }

    @Override
    public void setDescription(final String description) {
	this.description = description;
    }

    @Override
    public void setParentId(final String parentId) {
	this.parentId = parentId;
    }

    @Override
    public void setPosition(final int position) {
	this.position = position;
    }

    @Override
    public void setTitle(final String title) {
	this.title = title;
    }

    @Override
    public void setUserId(final String userId) {
	this.userId = userId;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

}
