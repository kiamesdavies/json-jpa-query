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
package com.github.markserrano.jsonquery.jpa.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import org.joda.time.DateTime;

/**
 * Retrieves the class type
 *
 * @author Mark Anthony L. Serrano
 */
public class ClassUtil {

    public static Class<?> getMatchingType(Field f, String field) {
        if (f.getName().equals(StringUtil.getLastField(field))) {
            return f.getType();
        }

        return null;
    }

    public static Class<?> getType(Class<?> clazz, String field) {
        for (Field f : clazz.getDeclaredFields()) {
            for (Field g : f.getType().getDeclaredFields()) {
                for (Field h : g.getType().getDeclaredFields()) {

                    Class<?> type = getMatchingType(h, field);
                    //when u have something like complaintTypeId.complaintTypeId without the other check apart from null, it will pick the type of the first complaintTypeId object
                    //added by james 
                    if (type != null && (type.isPrimitive() || type.getPackage().getName().equalsIgnoreCase("java.lang") || type == Date.class || type == DateTime.class  )) {
                        return type;
                    }
                }

                Class<?> type = getMatchingType(g, field);
                if (type != null) {
                    return type;
                }
            }

            Class<?> type = getMatchingType(f, field);
            if (type != null) {
                return type;
            }
        }

        Class<?> type = getTypeByParent(clazz, field);
        if (type != null) {
            return type;
        }

        throw new RuntimeException("No matching field for " + field + " and class " + clazz);
    }

    // Check by inheritance if no match
    public static Class<?> getTypeByParent(Class<?> clazz, String field) {
        Class<?> parentClazz = clazz.getSuperclass();
        while (parentClazz != null) {
            for (Field f : parentClazz.getDeclaredFields()) {
                for (Field g : f.getType().getDeclaredFields()) {
                    for (Field h : g.getType().getDeclaredFields()) {

                        Class<?> type = getMatchingType(h, field);
                        if (type != null) {
                            return type;
                        }
                    }

                    Class<?> type = getMatchingType(g, field);
                    if (type != null) {
                        return type;
                    }
                }

                Class<?> type = getMatchingType(f, field);
                if (type != null) {
                    return type;
                }
            }

            parentClazz = parentClazz.getSuperclass();
        }

        return null;
    }

    public static String getId(Class<?> clazz) {
        for (Field f : clazz.getDeclaredFields()) {
            for (Annotation an : f.getAnnotations()) {
                if (an instanceof javax.persistence.Id) {
                    return f.getName();
                }
            }
        }

        String id = getIdByParent(clazz);
        if (id != null) {
            return id;
        }

        throw new RuntimeException("No fk id found for field " + clazz);
    }

    public static String getIdByParent(Class<?> clazz) {
        Class<?> parentClazz = clazz.getSuperclass();
        while (parentClazz != null) {
            for (Field f : parentClazz.getDeclaredFields()) {
                for (Annotation an : f.getAnnotations()) {
                    if (an instanceof javax.persistence.Id) {
                        return f.getName();
                    }
                }
            }

            parentClazz = parentClazz.getSuperclass();
        }

        return null;
    }

    public static String getVariableName(Class<?> clazz) {
        return clazz.getSimpleName().substring(0, 1).toLowerCase()
                + clazz.getSimpleName().substring(1);
    }
}
