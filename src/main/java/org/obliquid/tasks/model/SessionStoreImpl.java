package org.obliquid.tasks.model;

import org.obliquid.tasks.interfaces.SessionStore;

import com.google.api.client.http.HttpRequestFactory;

/**
 * Session scoped bean
 * 
 * @author stivlo
 */
public class SessionStoreImpl implements SessionStore {
    
    private HttpRequestFactory requestFactory;

    @Override
    public HttpRequestFactory getRequestFactory() {
        return requestFactory;
    }

    @Override
    public void setRequestFactory(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;        
    }

    @Override
    public boolean isLoggedIn() {
        return requestFactory != null;
    }
    
    
 
}
