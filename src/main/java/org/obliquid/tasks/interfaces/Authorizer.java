package org.obliquid.tasks.interfaces;

import com.google.api.client.http.HttpRequestFactory;

/**
 * Authorize the user to access Google Services.
 * 
 * @author stivlo
 */
public interface Authorizer {

    /**
     * Build an URL pointing to Google, where the user can authorize our
     * application to use some services of the Google API.
     * 
     * @return the URL to authorize this app to use google services
     */
    String buildAuthorizationUrl();

    /**
     * Fetch a request factory that will provide authorization
     * 
     * @param authorizationCode
     *            code returned to the callback URL after calling the
     *            authorization URL
     * @return request factory
     */
    HttpRequestFactory fetchRequestFactory(String authorizationCode);

}
