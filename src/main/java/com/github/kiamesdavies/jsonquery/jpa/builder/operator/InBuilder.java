package com.github.kiamesdavies.jsonquery.jpa.builder.operator;

import com.github.kiamesdavies.jsonquery.jpa.builder.JunctionBuilder;
import com.github.kiamesdavies.jsonquery.jpa.enumeration.OperatorEnum;
import com.github.kiamesdavies.jsonquery.jpa.filter.JsonFilter;
import com.github.kiamesdavies.jsonquery.jpa.util.ClassUtil;
import com.github.kiamesdavies.jsonquery.jpa.util.PathUtil;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.StringPath;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InBuilder {

    public static BooleanBuilder get(Class<?> clazz, String variable, BooleanBuilder builder, JsonFilter.Rule rule) {

        if (OperatorEnum.getEnum(rule.getOp()) == OperatorEnum.IN) {
            if ( ClassUtil.getType(clazz, rule.getField()) == String.class ) {
                List<String> values = Arrays.asList(rule.getData().split(","));
                StringPath path = (StringPath) PathUtil.getPath(clazz, variable, rule.getField());
                return JunctionBuilder.getBuilder(path.in( values ), builder, rule);
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == Boolean.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == Integer.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == Long.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == Double.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == DateTime.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            if ( ClassUtil.getType(clazz, rule.getField()) == Date.class ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }

            // Check for custom objects by id
            if ( ClassUtil.getType(clazz, rule.getField())  != null ) {
                throw new RuntimeException("Operator not supported: " + rule.getOp());
            }
        }

        return null;
    }


}
