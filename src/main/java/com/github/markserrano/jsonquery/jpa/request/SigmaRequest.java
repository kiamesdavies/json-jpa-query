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

package com.github.markserrano.jsonquery.jpa.request;

import java.util.List;

/**
 * 
 * @author Mark Anthony L. Serrano
 */
public class SigmaRequest {

	private String recordType;
	private PageInfo pageInfo;
	private List<ColumnInfo> columnInfo;
	private List<SortInfo> sortInfo;
	private List<FilterInfo> filterInfo;
	private Parameters parameters;
	private Boolean remotePaging;
	private String action;
	
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<ColumnInfo> getColumnInfo() {
		return columnInfo;
	}

	public void setColumnInfo(List<ColumnInfo> columnInfo) {
		this.columnInfo = columnInfo;
	}

	public List<SortInfo> getSortInfo() {
		return sortInfo;
	}

	public void setSortInfo(List<SortInfo> sortInfo) {
		this.sortInfo = sortInfo;
	}

	public List<FilterInfo> getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(List<FilterInfo> filterInfo) {
		this.filterInfo = filterInfo;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Boolean getRemotePaging() {
		return remotePaging;
	}

	public void setRemotePaging(Boolean remotePaging) {
		this.remotePaging = remotePaging;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static class PageInfo {
		private Integer pageSize;
		private Integer pageNum;
		private Integer totalRowNum;
		private Integer totalPageNum;
		private Integer startRowNum;
		private Integer endRowNum;
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public Integer getPageNum() {
			return pageNum;
		}
		public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum;
		}
		public Integer getTotalRowNum() {
			return totalRowNum;
		}
		public void setTotalRowNum(Integer totalRowNum) {
			this.totalRowNum = totalRowNum;
		}
		public Integer getTotalPageNum() {
			return totalPageNum;
		}
		public void setTotalPageNum(Integer totalPageNum) {
			this.totalPageNum = totalPageNum;
		}
		public Integer getStartRowNum() {
			return startRowNum;
		}
		public void setStartRowNum(Integer startRowNum) {
			this.startRowNum = startRowNum;
		}
		public Integer getEndRowNum() {
			return endRowNum;
		}
		public void setEndRowNum(Integer endRowNum) {
			this.endRowNum = endRowNum;
		}
	}
	
	public static class ColumnInfo {
		private String id;
		private String header;
		private String fieldName;
		private String fieldIndex;
		private String sortOrder;
		private Boolean hidden;
		private Boolean exportable;
		private Boolean printable;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getHeader() {
			return header;
		}
		public void setHeader(String header) {
			this.header = header;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getFieldIndex() {
			return fieldIndex;
		}
		public void setFieldIndex(String fieldIndex) {
			this.fieldIndex = fieldIndex;
		}
		public String getSortOrder() {
			return sortOrder;
		}
		public void setSortOrder(String sortOrder) {
			this.sortOrder = sortOrder;
		}
		public Boolean getHidden() {
			return hidden;
		}
		public void setHidden(Boolean hidden) {
			this.hidden = hidden;
		}
		public Boolean getExportable() {
			return exportable;
		}
		public void setExportable(Boolean exportable) {
			this.exportable = exportable;
		}
		public Boolean getPrintable() {
			return printable;
		}
		public void setPrintable(Boolean printable) {
			this.printable = printable;
		}
	}
	
	public static class SortInfo {
	}
	
	public static class FilterInfo {
	}
	
	public static class Parameters {
		
	}
}
