package org.obliquid.tasks.interfaces;

import com.google.api.client.http.HttpRequestFactory;

/**
 * Access the session
 * 
 * @author stivlo
 * 
 */
public interface SessionStore {

    /**
     * Get the HttpRequestFactory from the session
     * 
     * @return the HttpRequestFactory or null if not present
     */
    HttpRequestFactory getRequestFactory();

    /**
     * Set the HttpRequestFactory in the session (this bean is session-scoped)
     * 
     * @param requestFactory
     */
    void setRequestFactory(HttpRequestFactory requestFactory);

    /**
     * Check whether the user is logged in
     * 
     * @return true if the user is logged in, false otherwise
     */
    boolean isLoggedIn();

}
