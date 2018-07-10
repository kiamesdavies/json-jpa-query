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
 * Enumeration for junctions
 * 
 * @author Mark Anthony L. Serrano
 */
public enum JunctionEnum
{
	AND(0, "and"), 
	OR(1, "or");
	
	private Integer value;
	private String description;
	private static final Map<Integer, JunctionEnum> lookupByInteger  = new HashMap<Integer, JunctionEnum>();
	private static final Map<String, JunctionEnum> lookupByString  = new HashMap<String, JunctionEnum>();	

	static {
        for(JunctionEnum r : EnumSet.allOf(JunctionEnum.class))
        	lookupByInteger.put(r.getValue(), r);
        
        for(JunctionEnum r : EnumSet.allOf(JunctionEnum.class))
        	lookupByString.put(r.getDescription(), r);
    }
	
	private JunctionEnum(Integer value, String description) {
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

	public static JunctionEnum getEnum(int code) { 
        return lookupByInteger.get(code); 
	}

	public static JunctionEnum getEnum(String code) {
		return getEnum(code, false);
	}

	public static JunctionEnum getEnum(String code, Boolean caseSensitive) {
		if (caseSensitive == true) {
			return lookupByString.get(code);
		} else {
			return lookupByString.get(code.toLowerCase());
		}
	}
}
