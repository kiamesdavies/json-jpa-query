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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.github.markserrano.jsonquery.jpa.enumeration.OperatorEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.mapper.JsonObjectMapper;

/**
 * A set of utilities for manipulating a JSON query {@link String}.
 * Currently, it has the following methods:
 * <pre>
 * 1. Appending of an AND query
 * 2. Appending of an OR query
 * </pre>
 * 
 * @author Mark Anthony L. Serrano
 */
public class QueryUtil {

	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String OPEN_BRACKET = "{";
	public static final String CLOSE_BRACKET = "}";
	public static final String FIELD = escape("field");
	public static final String OP = escape("op");
	public static final String DATA = escape("data");
	public static final String OR_JUNCTION = escape("junction") + COLON + escape("or");
	public static final String AND_JUNCTION = escape("junction") + COLON + escape("and");
	public static final String INIT_FILTER = "{\"groupOp\":\"AND\",\"rules\":[]}";
	
	public static String addSplitOr(String filters, String dtoField) {
		if (filters.contains(OPEN_BRACKET + FIELD + COLON + escape(dtoField) )) {
			
			String original_expression = null;
			String or_junction_expression = null;
			
			JsonFilter jqgridFilter = JsonObjectMapper.map(filters);
			
			int counter = 1;
			for (JsonFilter.Rule rule: jqgridFilter.getRules()) {
				if (rule.getField().equals(dtoField)) {
					
					
					String field = FIELD + COLON + escape(rule.getField()); // \"field\":\"id\"
					String field2 = FIELD + COLON + escape(rule.getField()+counter); // \"field\":\"id1\"
					String op = OP + COLON + escape(rule.getOp()); // \"op\":\"eq\"
					String eq = DATA + COLON + escape(rule.getData()); // \"data\":\"1\"

					// Basically we're trying to add an extra filter
					// {\"junction\":\"or\",\"field\":\"id\",\"op\":\"eq\",\"data\":\"1\"}
					or_junction_expression = OPEN_BRACKET+OR_JUNCTION+COMMA+field2+COMMA+op+COMMA+eq+CLOSE_BRACKET;
					
					// Retain original filter
					original_expression = OPEN_BRACKET+field+COMMA+op+COMMA+eq+CLOSE_BRACKET; 

					//logger.debug("expression: " + expression);
					if (or_junction_expression != null) {
						filters = filters.replace(original_expression, original_expression+","+or_junction_expression);
					}
					
					or_junction_expression = null;
					original_expression = null;
					counter += 1;
				}
			}

		}
		return filters;
	}
	
	public static String addAnd(String field, OperatorEnum operatorEnum, String data ) {
			// Target format: 
			// {\"junction\":\"and\",\"field\":\"id\",\"op\":\"eq\",\"data\":\"1\"}
					
			 String expression = OPEN_BRACKET + AND_JUNCTION + COMMA +
				FIELD + COLON + escape(field) + COMMA +
				OP + COLON + escape(operatorEnum.getDescription()) + COMMA +
				DATA + COLON + escape(data) + CLOSE_BRACKET;
			
			return expression;
	}
	
	public static String addAnd(String filter, String field, OperatorEnum operatorEnum, String data ) {
		filter = initFilter(filter);
		
		if (filter.contains("[{") == true) {
			return filter.replace("rules\":[", "rules\":["+addAnd(field, operatorEnum, data)+",");
		} else {
			return filter.replace("rules\":[", "rules\":["+addAnd(field, operatorEnum, data));
		}
	}
	
	public static String addOr(String field, OperatorEnum operatorEnum, String data ) {
		// Target format: 
		// {\"junction\":\"or\",\"field\":\"id\",\"op\":\"eq\",\"data\":\"1\"}
				
		 String expression = OPEN_BRACKET + OR_JUNCTION + COMMA +
			FIELD + COLON + escape(field) + COMMA +
			OP + COLON + escape(operatorEnum.getDescription()) + COMMA +
			DATA + COLON + escape(data) + CLOSE_BRACKET;
		
		return expression;
	}
	
	public static String addOr(String filter, String field, OperatorEnum operatorEnum, String data ) {
		filter = initFilter(filter);
		
		if (filter.contains("[{") == true) {
			return filter.replace("rules\":[", "rules\":["+addOr(field, operatorEnum, data)+",");
		} else {
			return filter.replace("rules\":[", "rules\":["+addOr(field, operatorEnum, data));
		}
	}

	public static Map<String, Object> remove(String filter, String field) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		filter = initFilter(filter);
		
		String pattern = "\\" + OPEN_BRACKET +
				FIELD + COLON + "(" + escape(field) + ")" + COMMA +
				OP + COLON + "(" + "\"[A-Za-z]{2}\"" + ")" + COMMA  +
				DATA + COLON + "(" + ".+" + ")" + "\\" + CLOSE_BRACKET;
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(filter);
		if (m.find()) {
		    map.put("field", m.group(1).replace("\"", ""));
		    map.put("op", m.group(2).replace("\"", ""));
		    map.put("data", m.group(3).split("\\}")[0].replace("\"", ""));
			map.put("isFound", true);
								
			String expression = OPEN_BRACKET +
							FIELD + COLON + escape(map.get("field").toString()) + COMMA +
							OP + COLON + escape(map.get("op").toString()) + COMMA +
							DATA + COLON + escape(map.get("data").toString()) + CLOSE_BRACKET;

			String f1 = filter.replace("," + expression, "");
			String f2 = f1.replace(expression + ",", "");
			String f3 = f2.replace(expression, "");
			map.put("parentFilter", f3);
		} else {
			map.put("parentFilter", filter);
		}
		
		return map;
	}
	
	public static Map<String, String> createParentAndChildFilter(String parentFilter, List<String> fieldsToRemove) {
		
		Map<String, String> filterMap = new HashMap<String, String>();
		
		String childFilter = initFilter(null);
		
		Map<String, Object> map = null;
		for (String f: fieldsToRemove) {
			if (map == null) {
				map = QueryUtil.remove(parentFilter, f);
			} else {
				map = QueryUtil.remove(map.get("parentFilter").toString(), f);
			}

			if (map.get("field") != null) {
				childFilter = QueryUtil.addAnd(childFilter, map.get("field").toString(), 
						OperatorEnum.getEnum(map.get("op").toString()), map.get("data").toString());
			}
		}
		
		filterMap.put("childFilter", childFilter);
		filterMap.put("parentFilter", map.get("parentFilter").toString());
		
		return filterMap;
	}

	public static String escape(String word) {
		return "\"" + word + "\"";
	}
	
	public static String init() {
		return initFilter(null);
	}
	
	public static String initFilter(String filter) {
		if (filter == null || filter.isEmpty() == true || filter.equals(" ")) {
			return INIT_FILTER;
		}
		
		return filter;
	}
	
}


