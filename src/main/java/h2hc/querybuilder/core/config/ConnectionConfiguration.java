package h2hc.querybuilder.core.config;


import lombok.Getter;
import lombok.Setter;

import java.sql.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isTrue;

@Setter
@Getter
public class ConnectionConfiguration {

    private String jdbcUrl;
    private String user;
    private String passwd;
    private Connection connection;

    public ConnectionConfiguration(String jdbcUrl, String user, String passwd) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.passwd = passwd;
    }

    public ResultSet sendQuery(String query) {
        ResultSet data = null;
        try {

            if (isNull(connection) || isTrue(connection.isClosed()))
                connection = DriverManager.getConnection(this.jdbcUrl, this.user, this.passwd);

            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            data = statement.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
