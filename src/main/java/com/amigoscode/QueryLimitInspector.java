package com.amigoscode;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class QueryLimitInspector implements StatementInspector {
    @Override
    public String inspect(String sql) {
        if(sql.toLowerCase().startsWith("select") &&
            !sql.toLowerCase().contains("limit")) {
            return sql + " LIMIT 1000";
        }
        return sql;
    }
}
