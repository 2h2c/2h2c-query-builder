package h2hc.querybuilder.core.conditions.impl;

import h2hc.querybuilder.core.conditions.IQuery;
import lombok.Data;

@Data
public class AndWhereQuery<T > implements IQuery {

    private String column;
    private T value;

    public AndWhereQuery(String column, T value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String processQuery() {
        return " AND " + column + " = " + (value.getClass() == String.class ?  "'" + value + "'" : "value" );
    }
}
