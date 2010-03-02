package net.boklab.core.client.model;

public class DelegatedBok implements Bok {
    protected final Bok delegate;
    private final String bokType;

    public DelegatedBok(final Bok delegate, final String bokType) {
	assert bokType.equals(delegate.getBokType()) : "Bok type doesn't match! (" + bokType + " != "
		+ delegate.getBokType() + ")";
	this.delegate = delegate;
	this.bokType = bokType;
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
    public String getContentType() {
	return delegate.getContentType();
    }

    @Override
    public String getDescription() {
	return delegate.getDescription();
    }

    @Override
    public String getId() {
	return delegate.getId();
    }

    public String getIdString() {
	return "" + getId();
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
    public String getWrapperType() {
	return bokType;
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
    public void setParentId(final String id) {
	delegate.setParentId(id);
    }

    @Override
    public void setPosition(final int position) {
	delegate.setPosition(position);
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
