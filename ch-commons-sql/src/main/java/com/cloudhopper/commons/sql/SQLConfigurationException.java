package com.cloudhopper.commons.sql;

/*
 * #%L
 * ch-commons-sql
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

import java.sql.SQLException;

/**
 * Thrown if an error occurs while configuring a DataSourceFactory or DataSource.
 * 
 * @author joelauer
 */
public class SQLConfigurationException extends SQLException {

    /**
     * Constructs an instance of <code>SQLConfigurationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SQLConfigurationException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>SQLConfigurationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SQLConfigurationException(String msg, Throwable t) {
        super(msg, t);
    }
}
