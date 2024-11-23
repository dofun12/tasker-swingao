package org.lemanoman;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetConverter<T> {
    public T convert(ResultSet rs) throws SQLException;
}
