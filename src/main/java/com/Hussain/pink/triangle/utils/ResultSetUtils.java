package com.Hussain.pink.triangle.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Hussain on 28/04/2015.
 */
public class ResultSetUtils {

    public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
        return !resultSet.first();
    }

    public static int getColumnCount(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        return resultSetMetaData.getColumnCount();
    }

    public static boolean checkIfColumnIsNull(ResultSet resultSet, String columnToCheck) throws SQLException {
        resultSet.getInt(columnToCheck);
        return resultSet.wasNull();
    }
}
