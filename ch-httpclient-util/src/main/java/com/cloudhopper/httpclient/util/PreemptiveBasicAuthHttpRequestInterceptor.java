package com.cloudhopper.httpclient.util;

/*
 * #%L
 * ch-httpclient-util
 * %%
 * Copyright (C) 2012 - 2013 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;

/**
 * HttpRequestInterceptor that will pre-emptively include "Basic" authentication
 * credentials with a request.
 *
 * @author joelauer
 */
public class PreemptiveBasicAuthHttpRequestInterceptor implements HttpRequestInterceptor {
    // basic auth scheme
    private BasicScheme basicAuthScheme;
    // username/password credentials
    private Credentials credentials;

    public PreemptiveBasicAuthHttpRequestInterceptor() {
        this.basicAuthScheme = new BasicScheme();
        this.credentials = null;
    }

    public void setCredentials(String username, String password) {
        this.credentials = new UsernamePasswordCredentials(username, password);
    }

    public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
        //
        // get the authentication state for the request
        //
        AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);

        //
        // if no auth scheme avaialble yet, try to initialize it preemptively
        //
        if (authState.getAuthScheme() == null) {
            // check if credentials are set
            if (this.credentials == null) {
                throw new HttpException("No credentials for preemptive authentication");
            }

            // add the credentials to the auth state
            authState.setAuthScheme(this.basicAuthScheme);
            authState.setCredentials(this.credentials);

            // HttpHost targetHost = (HttpHost)context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            //CredentialsProvider credsProvider = (CredentialsProvider)context.getAttribute(ClientContext.CREDS_PROVIDER);
        }
    }

}
