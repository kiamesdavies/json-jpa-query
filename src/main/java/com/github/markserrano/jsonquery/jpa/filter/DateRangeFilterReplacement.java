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

package com.github.markserrano.jsonquery.jpa.filter;

import java.util.Map;


import com.github.markserrano.jsonquery.jpa.util.FieldReplacementUtil;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;

/**
 * Generic implementation that requires a date range
 * 
 * @author Mark Anthony L. Serrano
 */
public abstract class DateRangeFilterReplacement implements FilterReplacement {

	protected String datefrom;
	protected String dateto;
	
	public DateRangeFilterReplacement(String datefrom, String dateto) {
		super();
		this.datefrom = datefrom;
		this.dateto = dateto;
	}
	
	@Override
	public String replace(String filter)  {
		if (filter == null) {
			filter = QueryUtil.init();
		}
		
		filter = preReplace(filter);
		filter = FieldReplacementUtil.forQuery(filter, getReplacementMap());
		filter = postReplace(filter);
		
		return filter;
	}	
	
	/**
	 * Field by field replacements should be declared here
	 * 
	 * @return a map
	 */
	public abstract Map<String, String> getReplacementMap();
	
	/**
	 * A pre hook before fields will be replaced
	 * 
	 * @param filter
	 * @return the filter
	 */
	public String preReplace(String filter) {
		return filter;
	}
	
	/**
	 * A post hook after the fields have replaced
	 * 
	 * @param filter
	 * @return the filter
	 */
	public String postReplace(String filter) {
		return filter;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	
}
