package com.solvd.Railway.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyPool {
    private static String url;
    private static String login;
    private static String password;
    private static String driver;
    private static int workingConnections = 0;
    private static int connectionsAmount = 10;
    private static BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(connectionsAmount, true);
    private static MyPool Instance;


    public MyPool(String driver, String url, String login, String password) throws SQLException {
        this.setDriver(driver);
        this.url = url;
        this.login = login;
        this.password = password;

        Connection connection = DriverManager.getConnection(url, login, password);
        connections.add(connection);

    }

    public static Connection getConnection() throws InterruptedException, SQLException {

        if (workingConnections <= connectionsAmount) {
            if (connections.size() == 0) {
                workingConnections++;
                connections.add(DriverManager.getConnection(url, login, password));
                return connections.take();

            } else if (connections.size() > 0) {
                workingConnections++;
                return connections.take();
            }
        } else {

            return connections.take();

        }

        return null;
    }

    public boolean releaseConnection(Connection c) throws InterruptedException {
        if (connections.size() < connectionsAmount) {
            workingConnections--;
            return connections.add(c);
        } else {
            return false;
        }
    }

    public boolean closeAllConnections() throws SQLException {

        if (connections.size() > 0) {
            for (Connection connection : connections) {
                connection.close();
            }
            return true;
        } else
            return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

}
