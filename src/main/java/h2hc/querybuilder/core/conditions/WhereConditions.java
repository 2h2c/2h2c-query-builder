package h2hc.querybuilder.core.conditions;

import h2hc.querybuilder.core.DataCollector;

import java.util.List;

public interface WhereConditions extends DataCollector {

    <T extends CharSequence> WhereConditions where(String column, T value);

    <T extends CharSequence> WhereConditions and(String column, T value);

    <T extends CharSequence> WhereConditions or(String column, T value);

    <T extends List> WhereConditions in(String column, T value);
}
