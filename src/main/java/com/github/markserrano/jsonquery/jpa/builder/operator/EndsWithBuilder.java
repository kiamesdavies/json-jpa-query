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

import java.util.Date;

import org.joda.time.DateTime;

import com.github.markserrano.jsonquery.jpa.builder.JunctionBuilder;
import com.github.markserrano.jsonquery.jpa.enumeration.OperatorEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.util.ClassUtil;
import com.github.markserrano.jsonquery.jpa.util.PathUtil;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.StringPath;

/**
 * Aggregates an existing {@link BooleanBuilder} with operator-type {@link Predicate}
 * 
 * @param clazz the domain class
 * @param variable the literal name of the class
 * @param builder an existing {@link BooleanBuilder}
 * @param rule a {@link JsonFilter.Rule} containing a field query
 * 
 * @author Mark Anthony L. Serrano
 */
public class EndsWithBuilder {

	public static BooleanBuilder get(Class<?> clazz, String variable, BooleanBuilder builder, JsonFilter.Rule rule) {
		
		if (OperatorEnum.getEnum(rule.getOp()) == OperatorEnum.ENDS_WITH) {
			if ( ClassUtil.getType(clazz, rule.getField()) == String.class ) {
				StringPath path = (StringPath) PathUtil.getPath(clazz, variable, rule.getField());
				return JunctionBuilder.getBuilder(path.endsWithIgnoreCase(rule.getData() ), builder, rule);
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == Boolean.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == Integer.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == Long.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == Double.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == DateTime.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
			
			if ( ClassUtil.getType(clazz, rule.getField()) == Date.class ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}

			// Check for custom objects by id
			if ( ClassUtil.getType(clazz, rule.getField())  != null ) {
				throw new RuntimeException("Operator not supported: " + rule.getOp());
			}
		}
		
		return null;
	}
}
