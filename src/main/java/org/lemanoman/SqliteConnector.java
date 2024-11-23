package org.lemanoman;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteConnector {
    final String url;

    public SqliteConnector(String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }


    public void execute(String sql) throws SQLException {
        try (
                Connection conn = getConnection();
                var pstmt = conn.createStatement()) {
            System.out.println("Executing: " + sql);
            pstmt.execute(sql);
        }
    }

    public <T> List<T> executeQuery(String sql, ResultSetConverter<T> converter) throws SQLException {
        List<T> result = new ArrayList<>();
        try (
                Connection conn = getConnection();
                var pstmt = conn.createStatement();
                ResultSet rs = pstmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(converter.convert(rs));
            }
        }
        return result;
    }

    public <T> List<T> executeQuery(String sql, ResultSetConverter<T> converter, StatementParam... params) throws SQLException {
        List<T> result = new ArrayList<>();
        try (
                Connection conn = getConnection();
                var pstmt = conn.prepareStatement(sql);
        ) {
            addParams(pstmt, params);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(converter.convert(rs));
                }
            }
        }
        return result;
    }

    private void addParams(PreparedStatement pstmt, StatementParam[] params) throws SQLException {
        for (var param : params) {
            System.out.println("Param: " + param.index() + " Value: " + param.value());
            if (param.type().equals(String.class)) {
                pstmt.setString(param.index(), (String) param.value());
            }
            if (param.type().equals(Integer.class)) {
                pstmt.setInt(param.index(), (Integer) (param.value()));
            }
            if (param.type().equals(Long.class)) {
                pstmt.setLong(param.index(), (Long) (param.value()));
            }
            if (param.type() == java.util.Date.class) {
                pstmt.setDate(param.index(), new Date(((java.util.Date) param.value()).getTime()));
            }
            if (param.type().equals(Boolean.class)) {
                pstmt.setBoolean(param.index(), (Boolean) param.value());
            }
            if (param.type().equals(Double.class)) {
                pstmt.setDouble(param.index(), (Double) param.value());
            }
            if (param.type().equals(Float.class)) {
                pstmt.setFloat(param.index(), (Float) param.value());
            }
            if (param.type().equals(byte[].class)) {
                pstmt.setBytes(param.index(), (byte[]) param.value());
            }
            if (param.type().equals(Timestamp.class)) {
                pstmt.setTimestamp(param.index(), (Timestamp) param.value());
            }
            if (param.type().equals(Time.class)) {
                pstmt.setTime(param.index(), (Time) param.value());
            }
        }
    }

    public ResultSet execute(String sql, boolean update, StatementParam... params) throws SQLException {
        if (params == null || params.length == 0) {
            try (
                    var conn = getConnection();
                    var statement = conn.createStatement();
            ) {

                return statement.executeQuery(sql);
            }
        }
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("Executing: " + sql);

            addParams(pstmt, params);

            if (update) {
                //pstmt.executeUpdate();
                pstmt.executeUpdate();
                return null;
            } else {
                return pstmt.executeQuery();
            }
        }
    }


}
