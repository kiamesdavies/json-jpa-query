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

/**
 * Utility for retrieving a specific path in a fully-qualified {@link String} class name
 * 
 * @author Mark Anthony L. Serrano
 */
public class StringUtil {
	
	
	public static String getFieldAtIndex(String path, Integer index) {
		String[] parts = path.split("\\.");
		
		if (parts.length > 1) {
			return parts[index];
		}

		return path;
	}
	
	public static String getLastField(String path) {
		String[] parts = path.split("\\.");
		
		if (parts.length > 1) {
			return parts[parts.length-1];
		}

		return path;
	}
}
