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
package com.github.markserrano.jsonquery.jpa.specifier;

import java.util.Date;

import org.joda.time.DateTime;

import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.util.ClassUtil;
import com.github.markserrano.jsonquery.jpa.util.PathUtil;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

/**
 * Retrieves an {
 *
 * @OrderSpecifier} for specifying the order of records.
 *
 * @param clazz the domain class
 * @param variable the literal name of the class
 * @param field the literal name of the field
 * @param orderEnum an {@link OrderEnum} value
 *
 * @author Mark Anthony L. Serrano
 */
public class Order {

    private Class<?> clazz;
    private String variable;

    public Order(Class<?> clazz) {
        super();
        this.clazz = clazz;
        this.variable = ClassUtil.getVariableName(clazz);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public OrderSpecifier<?> by(String field, OrderEnum orderEnum) {
        if (orderEnum == OrderEnum.ASC) {
            if (ClassUtil.getType(clazz, field) == String.class) {
                StringPath path = (StringPath) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

            if (ClassUtil.getType(clazz, field) == Integer.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }
            if (ClassUtil.getType(clazz, field) == Short.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }
            if (ClassUtil.getType(clazz, field) == Long.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

            if (ClassUtil.getType(clazz, field) == Double.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

            if (ClassUtil.getType(clazz, field) == DateTime.class) {
                DatePath<?> path = (DatePath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

            if (ClassUtil.getType(clazz, field) == Date.class) {
                DatePath<?> path = (DatePath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

            // Check for custom objects by id
            if (ClassUtil.getType(clazz, field) != null) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.asc();
            }

        } else if (orderEnum == OrderEnum.DESC) {
            if (ClassUtil.getType(clazz, field) == String.class) {
                StringPath path = (StringPath) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }

            if (ClassUtil.getType(clazz, field) == Short.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }
            if (ClassUtil.getType(clazz, field) == Integer.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }
            if (ClassUtil.getType(clazz, field) == Long.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }

            if (ClassUtil.getType(clazz, field) == Double.class) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }

            if (ClassUtil.getType(clazz, field) == DateTime.class) {
                DatePath<?> path = (DatePath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }

            if (ClassUtil.getType(clazz, field) == Date.class) {
                DatePath<?> path = (DatePath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }

            // Check for custom objects by id
            if (ClassUtil.getType(clazz, field) != null) {
                NumberPath<?> path = (NumberPath<?>) PathUtil.getPath(clazz, variable, field);
                return path.desc();
            }
        }

        return null;
    }
}
