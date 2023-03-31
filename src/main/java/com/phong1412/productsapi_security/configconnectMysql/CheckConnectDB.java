package com.phong1412.productsapi_security.configconnectMysql;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CheckConnectDB {
    private DataSource dataSource;

    public CheckConnectDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean checkConnect() throws SQLException {
        boolean isconnected = false;
        try(Connection connection = this.dataSource.getConnection()) {
            isconnected = true;
        }
        catch (SQLException sx)
        {
            System.out.println("Connect to mysql false: "+ sx);
        }
        return isconnected;
    }

}
