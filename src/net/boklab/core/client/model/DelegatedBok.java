package net.boklab.core.client.model;

public class DelegatedBok implements Bok {
    protected final Bok delegate;

    public DelegatedBok(Bok delegate, String bokType) {
	this.delegate = delegate;
	assert bokType.equals(delegate.getBokType()) : "Bok type doesn't match! (" + bokType + " != "
		+ delegate.getBokType() + ")";
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
    public String getTitle() {
	return delegate.getTitle();
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
    public void setBody(String body) {
	delegate.setBody(body);
    }

    @Override
    public void setBokType(String type) {
	assert false : "Only subclasess can change bok type";
    }

    @Override
    public void setDescription(String text) {
	delegate.setDescription(text);
    }

    @Override
    public void setParentId(String id) {
	delegate.setParentId(id);
    }

    @Override
    public void setTitle(String title) {
	delegate.setTitle(title);
    }

    @Override
    public void setUserId(String id) {
	delegate.setUserId(id);
    }

}
