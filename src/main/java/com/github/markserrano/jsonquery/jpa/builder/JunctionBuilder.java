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

import com.github.markserrano.jsonquery.jpa.enumeration.JunctionEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * Aggregates an existing {@link BooleanBuilder} with junction-type {@link Predicate}
 * 
 * @param booleanExpression an existing {@link BooleanExpression} containing other {@link Predicate}
 * @param builder an existing {@link BooleanBuilder}
 * @param rule a {@link JsonFilter.Rule} containing a field query
 * 
 * @author Mark Anthony L. Serrano
 */
public class JunctionBuilder {

	public static BooleanBuilder getBuilder(BooleanExpression booleanExpression, BooleanBuilder builder, JsonFilter.Rule rule) {
		if (rule.getJunction() == null) {
			return builder.and(booleanExpression);
		}
		
		if (JunctionEnum.getEnum(rule.getJunction()) == JunctionEnum.OR) {
			return builder.or(booleanExpression);
		}
		
		if (JunctionEnum.getEnum(rule.getJunction()) == JunctionEnum.AND) {
			return builder.and(booleanExpression);
		}

		return builder.and(booleanExpression);
	}
}
