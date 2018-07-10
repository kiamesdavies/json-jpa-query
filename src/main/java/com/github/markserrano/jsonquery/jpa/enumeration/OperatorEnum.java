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

package com.github.markserrano.jsonquery.jpa.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration for operators
 * 
 * @author Mark Anthony L. Serrano
 */
public enum OperatorEnum
{
	EQUAL(0, "eq"), 
	NOT_EQUAL(1, "ne"),
	LESS_THAN(2, "lt"),
	GREATER_THAN(3, "gt"),
	GREATER_EQUAL(4, "ge"),
	LESSER_EQUAL(5, "le"),
	ENDS_WITH(6, "ew"),
	BEGINS_WITH(7, "bw"),
	CONTAINS(8, "cn");
	
	private Integer value;
	private String description;
	private static final Map<Integer, OperatorEnum> lookupByInteger  = new HashMap<Integer, OperatorEnum>();
	private static final Map<String, OperatorEnum> lookupByString  = new HashMap<String, OperatorEnum>();	

	static {
        for(OperatorEnum r : EnumSet.allOf(OperatorEnum.class))
        	lookupByInteger.put(r.getValue(), r);
        
        for(OperatorEnum r : EnumSet.allOf(OperatorEnum.class))
        	lookupByString.put(r.getDescription(), r);
    }
	
	private OperatorEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static OperatorEnum getEnum(int code) { 
        return lookupByInteger.get(code); 
	}

	public static OperatorEnum getEnum(String code) {
		return getEnum(code, false);
	}

	public static OperatorEnum getEnum(String code, Boolean caseSensitive) {
		if (caseSensitive == true) {
			return lookupByString.get(code);
		} else {
			return lookupByString.get(code.toLowerCase());
		}
	}
}
