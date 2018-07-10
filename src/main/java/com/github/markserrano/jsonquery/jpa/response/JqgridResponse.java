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
import java.util.function.Function;
import java.util.stream.Collectors;
import mock.springframework.data.domain.Page;

/**
 * A POJO representing a jQgrid's jsonReader property.
 *
 * @see
 * <a href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON
 * Data</a>
 *
 * @author Mark Anthony L. Serrano
 */
public class JqgridResponse<T> implements Serializable {

    /**
     * Current page
     */
    private String page;

    /**
     * Total pages
     */
    private String total;

    /**
     * Total number of records
     */
    private String records;

    /**
     * Contains the actual data
     */
    private List<T> rows;

    public JqgridResponse() {
    }

    public static <T, U> JqgridResponse<T> responseBuilder(Function<U, T> converter, Page<U> results) {
        JqgridResponse<T> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(converter).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

    public JqgridResponse(String page, String total, String records,
            List<T> rows) {
        super();
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "JqgridResponse [page=" + page + ", total=" + total
                + ", records=" + records + "]";
    }
}
