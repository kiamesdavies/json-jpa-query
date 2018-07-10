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
package com.github.markserrano.jsonquery.jpa.builder.operator;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;

import com.github.markserrano.jsonquery.jpa.builder.JunctionBuilder;
import com.github.markserrano.jsonquery.jpa.enumeration.OperatorEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.util.ClassUtil;
import com.github.markserrano.jsonquery.jpa.util.DateTimeUtil;
import com.github.markserrano.jsonquery.jpa.util.PathUtil;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Aggregates an existing {@link BooleanBuilder} with operator-type
 * {@link Predicate}
 *
 * @param clazz the domain class
 * @param variable the literal name of the class
 * @param builder an existing {@link BooleanBuilder}
 * @param rule a {@link JsonFilter.Rule} containing a field query
 *
 * @author Mark Anthony L. Serrano
 */
public class NotEqualBuilder {

    @SuppressWarnings("unchecked")
    public static BooleanBuilder get(Class<?> clazz, String variable, BooleanBuilder builder, JsonFilter.Rule rule) {

        if (OperatorEnum.getEnum(rule.getOp()) == OperatorEnum.NOT_EQUAL) {
            if (ClassUtil.getType(clazz, rule.getField()) == String.class) {
                StringPath path = (StringPath) PathUtil.getPath(clazz, variable, rule.getField());
                if (rule.getData() == null) {
                    return JunctionBuilder.getBuilder(path.isNotNull(), builder, rule);
                }
                return JunctionBuilder.getBuilder(path.ne(rule.getData()), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == Boolean.class) {
                BooleanPath path = (BooleanPath) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.ne(Boolean.valueOf(rule.getData())), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == Integer.class) {
                NumberPath<Integer> path = (NumberPath<Integer>) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.ne(Integer.valueOf(rule.getData())), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == Long.class) {
                NumberPath<Long> path = (NumberPath<Long>) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.ne(Long.valueOf(rule.getData())), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == Double.class) {
                NumberPath<Double> path = (NumberPath<Double>) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.ne(Double.valueOf(rule.getData())), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == DateTime.class) {
                DatePath<DateTime> path = (DatePath<DateTime>) PathUtil.getPath(clazz, variable, rule.getField());
                DateTime dt = new DateTime(rule.getData());
                dt = DateTimeUtil.getDateTimeWithOffset(dt);
                return JunctionBuilder.getBuilder(path.ne(dt), builder, rule);
            }

            if (ClassUtil.getType(clazz, rule.getField()) == Date.class) {
                DatePath<Date> path = (DatePath<Date>) PathUtil.getPath(clazz, variable, rule.getField());
                Date dt;
                try {
                    dt = DateUtils.parseDate(rule.getData(), DateTimeUtil.SIMPLE_DATE_WITH_TIME_FORMAT.toPattern(), DateTimeUtil.SIMPLE_DATE_FORMAT.toPattern());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return JunctionBuilder.getBuilder(path.ne(dt), builder, rule);
            }

            // Check for custom objects by id
            if (ClassUtil.getType(clazz, rule.getField()) != null) {
                NumberPath<Long> path = (NumberPath<Long>) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.ne(Long.valueOf(rule.getData())), builder, rule);
            }

        }

        return null;
    }
}
