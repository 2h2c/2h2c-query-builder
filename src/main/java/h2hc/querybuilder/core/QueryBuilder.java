package h2hc.querybuilder.core;

import h2hc.querybuilder.core.conditions.IQuery;
import h2hc.querybuilder.core.conditions.WhereConditions;

import java.util.List;

public interface QueryBuilder  {

    QueryManager table(String table);

    QueryManager table(String table, String schema);

    QueryManager select(String... columns);

    QueryBuilder connect(String jdbcUrl, String user, String passwd);

    <T extends Number & CharSequence> WhereConditions where(String column, T value);

    <T extends Number & CharSequence> WhereConditions and(String column, T value);

    <T extends Number & CharSequence> WhereConditions or(String column, T value);

    <T extends List> WhereConditions in(String column, T value);

    List collectData();
}
