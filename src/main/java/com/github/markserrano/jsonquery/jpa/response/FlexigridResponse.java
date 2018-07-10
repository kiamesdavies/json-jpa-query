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

/**
 * @author Mark Anthony L. Serrano
 */
public class FlexigridResponse<T extends Serializable>  implements Serializable  {

	private Integer page;
	private Integer total;
	private List<FlexigridCell<T>> rows;

	public FlexigridResponse() {}
	
	public FlexigridResponse(Integer page, Integer total,
			List<FlexigridCell<T>> rows) {
		super();
		this.page = page;
		this.total = total;
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<FlexigridCell<T>> getRows() {
		return rows;
	}

	public void setRows(List<FlexigridCell<T>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "DataTablesResponse [page=" + page + ", total=" + total
				+ ", rows=" + rows + "]";
	}
	
	public static class FlexigridCell<T extends Serializable>{

		private Long id;
		private T cell;

		public FlexigridCell() {}
		
		public FlexigridCell(Long id,
				T cell) {
			super();
			this.id = id;
			this.cell = cell;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public T getCell() {
			return cell;
		}

		public void setCell(T cell) {
			this.cell = cell;
		}

		@Override
		public String toString() {
			return "FlexigridCellResponse [id=" + id + ", cell=" + cell
					+ "]";
		}
	}
}
