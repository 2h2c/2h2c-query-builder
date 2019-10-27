package h2hc.querybuilder.core.conditions.impl;

import h2hc.querybuilder.core.conditions.IQuery;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

@Data
public class InWhereQuery<T extends List> implements IQuery {

    private String column;
    private T value;

    public InWhereQuery(String column, T value ) {
        this.column = column;
        this.value = value;
     }

    @Override
    public String processQuery() {
        //TODO sin acabar
     return null;
    }
}
