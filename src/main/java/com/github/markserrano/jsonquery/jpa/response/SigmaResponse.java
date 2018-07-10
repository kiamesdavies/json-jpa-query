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

package com.github.markserrano.jsonquery.jpa.response;

import java.io.Serializable;
import java.util.List;

public class SigmaResponse <T extends Serializable>  implements Serializable {

	private List<T> data;
	private PageInfo pageInfo;
	private String exception;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
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
}
