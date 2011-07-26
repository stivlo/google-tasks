package org.obliquid.tasks.model;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.obliquid.tasks.interfaces.Authorizer;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * OAuth2 Authorizer implementation
 * 
 * @author stivlo
 * 
 */
public class OAuth2Authorizer implements Authorizer {

    /** Google API Client Id */
    private String clientId;

    /** Google API Client Secret */
    private String clientSecret;

    /** Absolute callback URL on our app */
    private String callbackUrl;

    /** A space separated list of application URLs to authorize */
    private String scope;

    /** HttpTransport to fetch remote URLs */
    private HttpTransport transport;

    /** JSON parser factory */
    private JsonFactory jsonFactory;

    /** Logger instance */
    private static final Logger log = Logger.getLogger(OAuth2Authorizer.class);

    /**
     * Constructor with all parameters needed to build the object
     * 
     * @param clientId
     *            Google API Client Id
     * @param clientSecret
     *            Google API Client Secret
     * @param callbackUrl
     *            Absolute callback URL starting with http:// or https://, this
     *            callback URL will have a GET parameter, called 'code',
     *            containing the authorization code needed to call
     *            fetchAccessToken() method
     * @param scope
     *            a space separated list of application URLs to authorize
     */
    public OAuth2Authorizer(String clientId, String clientSecret, String callbackUrl, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.callbackUrl = callbackUrl;
        this.scope = scope;
    }

    public void setTransport(HttpTransport transport) {
        this.transport = transport;
    }

    public void setJsonFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Override
    public String buildAuthorizationUrl() {
        String authorizeUrl = new GoogleAuthorizationRequestUrl(clientId, callbackUrl, scope).build();
        return authorizeUrl;
    }

    @Override
    public HttpRequestFactory fetchRequestFactory(String authorizationCode) {
        HttpRequestFactory rf = null;
        GoogleAuthorizationCodeGrant authRequest = new GoogleAuthorizationCodeGrant(transport, jsonFactory,
                clientId, clientSecret, authorizationCode, callbackUrl);
        authRequest.useBasicAuthorization = false;
        AccessTokenResponse authResponse;
        try {
            authResponse = authRequest.execute();
            String accessToken = authResponse.accessToken;
            GoogleAccessProtectedResource access = new GoogleAccessProtectedResource(accessToken, transport,
                    jsonFactory, clientId, clientSecret, authResponse.refreshToken);
            rf = transport.createRequestFactory(access);
        } catch (IOException ex) {
            log.error("fetchAccessToken() failed", ex); // token will be null
        }
        return rf;
    }

}
