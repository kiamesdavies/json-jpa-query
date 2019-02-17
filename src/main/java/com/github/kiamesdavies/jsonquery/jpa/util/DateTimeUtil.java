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
package com.github.kiamesdavies.jsonquery.jpa.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.github.kiamesdavies.jsonquery.jpa.enumeration.OperatorEnum;
import com.github.kiamesdavies.jsonquery.jpa.filter.JsonFilter;
import com.github.kiamesdavies.jsonquery.jpa.mapper.JsonObjectMapper;

/**
 * Retrieves the correct {@link DateTime} for the current timezone
 *
 * @author Mark Anthony L. Serrano
 */
public class DateTimeUtil {

    public final static SimpleDateFormat SIMPLE_DATE_WITH_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public final static TimeZone TIMEZONE = TimeZone.getTimeZone("UTC");

    public static DateTime getDateTimeWithOffset(DateTime dt) {
        DateTime dateTime = dt.withZone(DateTimeZone.forTimeZone(TIMEZONE));
        return normalize(dateTime);
    }

    public static DateTime normalize(DateTime dateTime) {
        if (dateTime.toString().matches(".+(\\+)[0-9][0-9]:[0-9][0-9]")) {
            dateTime = dateTime.minusHours(dateTime.getHourOfDay());

        } else if (dateTime.toString().matches(".+(\\-)[0-9][0-9]:[0-9][0-9]")) {
            dateTime = dateTime.plusHours(24 - dateTime.getHourOfDay());
        }
        return dateTime;
    }

    public static Map<String, DateTime> getYesterdaySpan() {
        DateTime now = new DateTime();

        DateTime from = now.minusDays(1)
                .minusHours(now.getHourOfDay())
                .minusMinutes(now.getMinuteOfHour())
                .minusSeconds(now.getSecondOfMinute())
                .minusMillis(now.getMillisOfSecond());

        DateTime to = from.plusHours(23)
                .plusMinutes(59)
                .plusSeconds(59);

        Map<String, DateTime> map = new HashMap<String, DateTime>();
        map.put("from", from);
        map.put("to", to);

        return map;
    }

    public static String getDateTimeAsString(String filter, String datefield) {
        JsonFilter jsonFilter = JsonObjectMapper.map(filter);

        for (JsonFilter.Rule rule : jsonFilter.getRules()) {
            if (rule.getField().equals(datefield)) {
                return rule.getData();
            }
        }

        return null;
    }

    public static DateTime getDateTime(String filter, String datefield) {
        JsonFilter jsonFilter = JsonObjectMapper.map(filter);

        for (JsonFilter.Rule rule : jsonFilter.getRules()) {
            if (rule.getField().equals(datefield)) {
                return new DateTime(rule.getData());
            }
        }

        return null;
    }

    public static String toDateRangeQuery(String filter, String datefield) {
        DateTime from = DateTimeUtil.getDateTime(filter, datefield);
        DateTime to = from.plusHours(23).plusMinutes(59).plusSeconds(59);

        // Replace operator from "eq" to "ge"
        filter = filter.replace("\"field\":\"" + datefield + "\",\"op\":\"eq\"", "\"field\":\"" + datefield + "\",\"op\":\"ge\"");
        filter = QueryUtil.addAnd(filter, datefield, OperatorEnum.LESSER_EQUAL, to.toString());

        return filter;
    }
}
