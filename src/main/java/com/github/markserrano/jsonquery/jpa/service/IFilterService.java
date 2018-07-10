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
package com.github.markserrano.jsonquery.jpa.service;

import java.io.Serializable;
import java.util.List;

import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.Pageable;

import com.github.markserrano.jsonquery.jpa.builder.JsonBooleanBuilder;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;

/**
 *
 * @author Mark Anthony L. Serrano
 */
public interface IFilterService<T extends Serializable> {

    T find(BooleanBuilder builder, Class<T> clazz);

    Page<T> readAndCount(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order);

    List<T> readLeftJoin(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    List<T> read(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order);

    Long count(BooleanBuilder builder, Class<T> clazz);

    Long count(BooleanBuilder builder, Class<T> clazz, OrderSpecifier order);

    Page<T> read(String filter, Class<T> clazz, Pageable page, OrderSpecifier order);

    JsonBooleanBuilder getJsonBooleanBuilder(Class<T> clazz);

    List<T> read(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    List<T> readInnerJoin(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Long count(BooleanBuilder builder, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Long countInnerJoin(BooleanBuilder builder, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Long countLeftJoin(BooleanBuilder builder, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Page<T> readAndCount(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Page<T> readAndCountInnerJoin(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    Page<T> readAndCountLeftJoin(BooleanBuilder builder, Pageable page, Class<T> clazz, OrderSpecifier order, BooleanBuilder joinChildBuilder, String joinChildField, Class<?> joinChildClass);

    T findOne(BooleanBuilder builder, Class<T> clazz);

    Page<T> readAndCount(String filter, Pageable page, Class<T> clazz,
            OrderSpecifier order, String joinChildField,
            Class<?> joinChildClass, List<String> childFields);

}
