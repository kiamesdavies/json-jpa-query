/*
 * Copyright 2011-2012 the original author or authors.
 *
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
 */
package com.github.kiamesdavies.jsonquery.jpa.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.kiamesdavies.jsonquery.jpa.filter.JsonFilter;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Maps a JSON query {@link String} to a {@link JsonFilter} instance
 *
 *
 * @author Mark Anthony L. Serrano
 */
public class JsonObjectMapper {

    public final static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static JsonFilter map(String jsonString) {

        if (jsonString != null) {
            ObjectMapper mapper = new ObjectMapper();
            DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
            mapper.setDateFormat(DATEFORMAT);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            try {
                return mapper.readValue(jsonString, JsonFilter.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
