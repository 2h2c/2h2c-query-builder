package h2hc.querybuilder.core;

import h2hc.querybuilder.core.conditions.IQuery;
import h2hc.querybuilder.core.conditions.WhereConditions;
import h2hc.querybuilder.core.conditions.impl.SimpleWhereQuery;

import java.util.List;

public interface QueryManager extends WhereConditions, DataCollector {

    QueryManager table(String table, String schema);

    QueryManager select(String[] columns);

 }
