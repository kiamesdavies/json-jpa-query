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
package com.github.kiamesdavies.jsonquery.jpa.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;

/**
 * Retrieves the corresponding {@link Path} for the provided field
 *
 * @param clazz the domain class
 * @param variable the literal name of the class
 * @param field the literal field name
 *
 * @author Mark Anthony L. Serrano
 */
public class PathUtil {

    public static Path<?> getPath(Class<?> clazz, String variable, String field) {

        @SuppressWarnings({"unchecked", "rawtypes"})
        PathBuilder<?> entityPath = new PathBuilder(clazz, variable);

        if (ClassUtil.getType(clazz, field) == String.class) {
            return entityPath.get(new StringPath(field));

        } else if (ClassUtil.getType(clazz, field) == Boolean.class || ClassUtil.getType(clazz, field).equals(boolean.class)) {
            return entityPath.get(new BooleanPath(field));

        } else if (ClassUtil.getType(clazz, field) == Integer.class) {
            return entityPath.get(new NumberPath<Integer>(Integer.class, field));

        } else if (ClassUtil.getType(clazz, field) == Long.class) {
            return entityPath.get(new NumberPath<Long>(Long.class, field));

        } else if (ClassUtil.getType(clazz, field) == Double.class) {
            return entityPath.get(new NumberPath<Double>(Double.class, field));

        } else if (ClassUtil.getType(clazz, field) == DateTime.class) {
            return entityPath.get(new DatePath<DateTime>(DateTime.class, field));

        } else if (ClassUtil.getType(clazz, field) == Date.class) {
            return entityPath.get(new DatePath<Date>(Date.class, field));

        } else if (ClassUtil.getType(clazz, field) == Serializable.class) {
            // For references on another objects, we assume that we reference by id
            // And the type is Long and Serializable
            return entityPath.get(new NumberPath<Long>(Long.class, field));

        } else if (ClassUtil.getType(clazz, field) == List.class) {
            // For references on another objects, we assume that we reference by id
            // And the type is Long and Serializable
            return entityPath.get(new NumberPath<Long>(Long.class, field));
        }

        throw new RuntimeException("No matching path for " + field +" and path "+clazz);
    }
}
