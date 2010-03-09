package net.boklab.core.client.model;

import java.util.ArrayList;

public class DelegatedBok implements Bok {
    public static Bok build(final BokResponseJSO response) {
	return new DelegatedBok(response.getBok(), getChildren(response));
    }

    private static ArrayList<Bok> getChildren(final BokResponseJSO response) {
	final ArrayList<Bok> children = new ArrayList<Bok>();
	final int total = response.getChildrenSize();
	for (int index = 0; index < total; index++) {
	    children.add(new DelegatedBok(response.getChildren(index)));
	}
	return children;
    }

    protected final BokDTO delegate;

    private final ArrayList<Bok> children;
    private Bok parent;

    public DelegatedBok(final BokDTO delegate) {
	this(delegate, new ArrayList<Bok>());
    }

    protected DelegatedBok(final BokDTO delegate, final ArrayList<Bok> children) {
	this.delegate = delegate;
	this.children = children;
    }

    @Override
    public BokDTO createBok(final String type, final String title, final String parentId,
	    final String userId, final int position) {
	return delegate.createBok(type, title, parentId, userId, position);
    }

    @Override
    public String getBody() {
	return delegate.getBody();
    }

    @Override
    public String getBokType() {
	return delegate.getBokType();
    }

    @Override
    public ArrayList<Bok> getChildren() {
	return children;
    }

    @Override
    public String getContentType() {
	return delegate.getContentType();
    }

    @Override
    public String getDescription() {
	return delegate.getDescription();
    }

    @Override
    public Bok getFirstChild(final String bokType) {
	for (final Bok child : getChildren()) {
	    if (child.getBokType().equals(bokType)) {
		return child;
	    }
	}
	return null;
    }

    @Override
    public String getId() {
	return delegate.getId();
    }

    public String getIdString() {
	return "" + getId();
    }

    @Override
    public Bok getParent() {
	return parent;
    }

    @Override
    public String getParentId() {
	return delegate.getParentId();
    }

    @Override
    public int getPosition() {
	return delegate.getPosition();
    }

    @Override
    public String getProjectId() {
	return delegate.getProjectId();
    }

    @Override
    public String getTitle() {
	return delegate.getTitle();
    }

    @Override
    public String getUpdatedAt() {
	return delegate.getUpdatedAt();
    }

    @Override
    public String getUserId() {
	return delegate.getUserId();
    }

    @Override
    public String getUserName() {
	return delegate.getUserName();
    }

    @Override
    public DelegatedBok newChild(final String type, final String title, final String userId,
	    final int position) {
	return new DelegatedBok(delegate.createBok(type, title, getId(), userId, position));
    }

    @Override
    public void setBody(final String body) {
	delegate.setBody(body);
    }

    @Override
    public void setBokType(final String type) {
	delegate.setBokType(type);
    }

    @Override
    public void setContentType(final String contentType) {
	delegate.setContentType(contentType);
    }

    @Override
    public void setDescription(final String text) {
	delegate.setDescription(text);
    }

    @Override
    public void setParent(final Bok parent) {
	this.parent = parent;
    }

    @Override
    public void setParentId(final String id) {
	delegate.setParentId(id);
    }

    @Override
    public void setPosition(final int position) {
	delegate.setPosition(position);
    }

    @Override
    public void setProjectId(final String projectId) {
	delegate.setProjectId(projectId);
    }

    @Override
    public void setTitle(final String title) {
	delegate.setTitle(title);
    }

    @Override
    public void setUserId(final String id) {
	delegate.setUserId(id);
    }

    public String toDebugString() {
	String name = this.getClass().getName();
	name = name.substring(name.lastIndexOf(".") + 1);
	return name + "(" + getId() + "): ";
    }

}
