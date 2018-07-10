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
package com.github.markserrano.jsonquery.jpa.builder;

import com.github.markserrano.jsonquery.jpa.builder.operator.BeginsWithBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.ContainsBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.EndsWithBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.EqualBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.GreaterEqualBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.GreaterThanBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.LessThanBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.LesserEqualBuilder;
import com.github.markserrano.jsonquery.jpa.builder.operator.NotEqualBuilder;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.mapper.JsonObjectMapper;
import com.github.markserrano.jsonquery.jpa.util.ClassUtil;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.mysema.query.BooleanBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Produces a {@link BooleanBuilder} artifact from a JSON query {@link String}
 *
 * @param <T> the domain class
 * @param clazz the domain class
 * @param variable the literal name of the domain class
 *
 * @author Mark Anthony L. Serrano
 */
public class JsonBooleanBuilder {

    private Class<?> clazz;
    private String variable;

    public JsonBooleanBuilder(Class<?> clazz) {
        super();
        this.clazz = clazz;
        this.variable = ClassUtil.getVariableName(clazz);
    }

    public BooleanBuilder build(JsonFilter jsonFilter) {
        if (jsonFilter.getSource() == null) {
            jsonFilter.setSource(QueryUtil.init());
            //throw new RuntimeException("Source filter is null!");
        }
        //if the JsonFilter has rules i.e not empty means that it has already been built
        JsonFilter filter = jsonFilter.getRules() == null || jsonFilter.getRules().isEmpty() ? JsonObjectMapper.map(jsonFilter.getSource()) : jsonFilter;

        BooleanBuilder builder = new BooleanBuilder();

        if (filter.getRules() != null) {
            for (JsonFilter.Rule rule : filter.getRules()) {
                rule.setJunction(filter.getGroupOp());
                builder = build(builder, rule);
            }
        }

        return builder;
    }

    public BooleanBuilder buildAndBlock(JsonFilter.Rule forceRule, JsonFilter jsonFilter) {
        return this.buildAndBlock(Arrays.asList(forceRule), jsonFilter);

    }

    public BooleanBuilder buildAndBlock(List<JsonFilter.Rule> forceRules, JsonFilter jsonFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (JsonFilter.Rule rule : forceRules) {
            booleanBuilder = booleanBuilder.and(build(booleanBuilder, rule));
        }
        if (jsonFilter.getGroupOp() != null && jsonFilter.getGroupOp().equalsIgnoreCase("OR")) {
            JsonFilter filter = JsonObjectMapper.map(jsonFilter.getSource());
            ArrayList<BooleanBuilder> builders = new ArrayList<>();
            filter.getRules().stream().forEach((rule1) -> {
                rule1.setJunction(filter.getGroupOp());
                builders.add(build(new BooleanBuilder(), rule1));
            });
            return booleanBuilder.andAnyOf(builders.toArray(new BooleanBuilder[]{}));
        }
        return booleanBuilder.and(build(jsonFilter));

    }

    public BooleanBuilder buildOrBlock(List<JsonFilter.Rule> forceRules, JsonFilter jsonFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (JsonFilter.Rule rule : forceRules) {
            booleanBuilder = booleanBuilder.or(build(booleanBuilder, rule));
        }
        if (jsonFilter.getGroupOp() != null && jsonFilter.getGroupOp().equalsIgnoreCase("OR")) {
            JsonFilter filter = JsonObjectMapper.map(jsonFilter.getSource());
            ArrayList<BooleanBuilder> builders = new ArrayList<>();
            filter.getRules().stream().forEach((rule1) -> {
                rule1.setJunction(filter.getGroupOp());
                builders.add(build(new BooleanBuilder(), rule1));
            });
            return booleanBuilder.andAnyOf(builders.toArray(new BooleanBuilder[]{}));
        }
        return booleanBuilder.and(build(jsonFilter));

    }

    public BooleanBuilder buildAndAnyOfBlock(List<JsonFilter.Rule> andRules, List<JsonFilter.Rule> anyOf, JsonFilter jsonFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (JsonFilter.Rule rule : andRules) {
            booleanBuilder = booleanBuilder.and(build(booleanBuilder, rule));
        }
        ArrayList<BooleanBuilder> builders = new ArrayList<>();
        anyOf.stream().forEach((rule1) -> {
            builders.add(build(new BooleanBuilder(), rule1));
        });
        return booleanBuilder.and(build(jsonFilter)).andAnyOf(builders.toArray(new BooleanBuilder[]{}));

    }

    public BooleanBuilder build(BooleanBuilder builder, JsonFilter.Rule rule) {
        BooleanBuilder tempBuilder = EqualBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = NotEqualBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = LessThanBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = GreaterThanBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = LesserEqualBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = GreaterEqualBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = EndsWithBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = BeginsWithBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        tempBuilder = ContainsBuilder.get(clazz, variable, builder, rule);
        if (tempBuilder != null) {
            return tempBuilder;
        }

        throw new RuntimeException("Unexpected operator [" + rule.getOp() + "] and field [" + rule.getField() + "]");
    }
}
