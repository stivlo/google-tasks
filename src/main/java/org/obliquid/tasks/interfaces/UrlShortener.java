package org.obliquid.tasks.interfaces;

import com.google.api.client.http.HttpRequestFactory;

/**
 * Shorten URL with goo.gl
 * 
 * @author stivlo
 * 
 */
public interface UrlShortener {
    
    /**
     * Set the request factory, which is needed to shorten URLs
     * 
     * @param requestFactory the HttpRequestFactory
     */
    void setHttpRequestFactory(HttpRequestFactory requestFactory);
    
    /**
     * Shorten the URL specified
     * 
     * @param url
     *            URL to shorten
     * @return the shortened URL
     */
    String shorten(String url);

}
