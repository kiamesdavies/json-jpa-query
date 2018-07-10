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

import java.util.Map;
import java.util.Map.Entry;

import com.github.markserrano.jsonquery.jpa.enumeration.OperatorEnum;

public class FieldReplacementUtil {
	public static final String QUOTE = "\"";
	
	public static String forQuery(String filter, Map<String, String> map) {
		
		for (Entry<String, String> entry : map.entrySet()) {
			filter = filter.replace(QUOTE+entry.getKey()+QUOTE, QUOTE+entry.getValue()+QUOTE);
		}
		
		return filter;
	}
	
	public static String forOrder(String filter, Map<String, String> map) {
		
		for (Entry<String, String> entry : map.entrySet()) {
			filter = filter.replace(entry.getKey(), entry.getValue());
		}
		
		return filter;
	}
	
	public static String forDateRange(String filter, String field) {
		filter = DateTimeUtil.toDateRangeQuery(filter, field);
		return filter;
	}
	
	public static String forDateRange(String filter, String field, String datefrom, String dateto) {
		filter = QueryUtil.addAnd(filter, field, OperatorEnum.GREATER_EQUAL, datefrom);
		filter = QueryUtil.addAnd(filter, field, OperatorEnum.LESSER_EQUAL, dateto);
		return filter;
	}
	
	public static Boolean doesFieldAndOpExists(String filter, String field, String op) {
		if (filter.contains("\"field\":\""+field+"\",\"op\":\""+op+"\"")) {
			return true;
		}
		return false;
	}
}
