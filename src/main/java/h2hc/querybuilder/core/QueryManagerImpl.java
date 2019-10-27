package h2hc.querybuilder.core;

import h2hc.querybuilder.core.conditions.IQuery;
import h2hc.querybuilder.core.conditions.WhereConditions;
import h2hc.querybuilder.core.conditions.impl.AndWhereQuery;
import h2hc.querybuilder.core.conditions.impl.InWhereQuery;
import h2hc.querybuilder.core.conditions.impl.OrWhereQuery;
import h2hc.querybuilder.core.conditions.impl.SimpleWhereQuery;
import h2hc.querybuilder.core.config.ConnectionConfiguration;
import jdk.nashorn.internal.objects.annotations.Where;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class QueryManagerImpl implements QueryManager {

    private ConnectionConfiguration connection;

    private String table, schema;
    private String[] columns;
    private List<IQuery> whereOptions;

    public QueryManagerImpl(ConnectionConfiguration connection) {
        this.connection = connection;
    }

    @Override
    public QueryManager table(String table, String schema) {
        this.table = table;
        this.schema = schema;
        return this;
    }

    @Override
    public QueryManager select(String[] columns) {
        this.columns = columns;
        return this;
    }

    public <T> WhereConditions where(IQuery query) {
        if (isNull(whereOptions)) whereOptions = new ArrayList<>();
        whereOptions.add(query);
        return this;
    }

    @Override
    public <T extends CharSequence> WhereConditions where(String column, T value) {
        return where(new SimpleWhereQuery<>(column, value));
    }

    @Override
    public <T extends CharSequence> WhereConditions and(String column, T value) {
        return where(new AndWhereQuery<>(column, value));
    }

    @Override
    public <T extends CharSequence> WhereConditions or(String column, T value) {
        return where(new OrWhereQuery<>(column, value));
    }

    @Override
    public <T extends List> WhereConditions in(String column, T value) {
        return where(new InWhereQuery<>(column, value));
    }

    @Override
    public List collectData() {

        ResultSet data = connection.sendQuery(getQuery());
        List<List> result = null;

        try {
            if (isNull(result)) result = new ArrayList<>();
            while (data.next()) {
                result.add(Arrays.stream(columns)
                        .map(col -> {
                            try {
                                return data.getObject(col);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getQuery() {

        String select = "SELECT " + Arrays.stream(columns).map(col -> col + ", ")
                .collect(Collectors.joining())
                .replaceAll(", $", " ");

        String from = isNull(schema) ? String.format(" FROM %s ", table) : String.format(" FROM %s.%s ", schema, table);

        String where = " WHERE " + whereOptions.stream().map(IQuery::processQuery).collect(Collectors.joining());

        return select + from + where;

    }


}
