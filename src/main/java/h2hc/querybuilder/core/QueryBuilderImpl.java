package h2hc.querybuilder.core;

import h2hc.querybuilder.core.conditions.WhereConditions;
import h2hc.querybuilder.core.config.ConnectionConfiguration;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class QueryBuilderImpl implements QueryBuilder {

    private QueryManager queryManager;
    private ConnectionConfiguration connection;

    @Override
    public QueryManager table(String table) {
        notNull(table, "cannot be null");
        return queryManager.table(table, null);
    }

    @Override
    public QueryManager table(String table, String schema) {
        notNull(table, "cannot be null");
        return queryManager.table(table, schema);
    }

    @Override
    public QueryManager select(String... columns) {
        isFalse(ArrayUtils.isEmpty(columns));
        return queryManager.select(columns);
    }

    @Override
    public QueryBuilder connect(String jdbcUrl, String user, String passwd) {

        notNull(jdbcUrl, "cannot be null");
        notNull(user, "cannot be null");
        notNull(passwd, "cannot be null");

        this.connection = new ConnectionConfiguration(jdbcUrl, user, passwd);
        this.queryManager = new QueryManagerImpl(connection);

        return this;
    }

    @Override
    public <T extends Number & CharSequence> WhereConditions where(String column, T value) {
        return queryManager.where(column, value);
    }

    @Override
    public <T extends Number & CharSequence> WhereConditions and(String column, T value) {
        return queryManager.and(column, value);
    }

    @Override
    public <T extends Number & CharSequence> WhereConditions or(String column, T value) {
        return queryManager.or(column, value);
    }

    @Override
    public <T extends List> WhereConditions in(String column, T value) {
        return queryManager.in(column, value);
    }

    @Override
    public List collectData() {
        return  queryManager.collectData();
    }

}
