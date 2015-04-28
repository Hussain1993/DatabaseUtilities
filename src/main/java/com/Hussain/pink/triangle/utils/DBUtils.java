package com.Hussain.pink.triangle.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Hussain on 28/04/2015.
 */
public class DBUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DBUtils.class);

    private static DBUtils instance;
    private static final Object instanceLock = new Object();

    private String classnameKey = "classname";
    private String usernameKey = "username";
    private String passwordKey = "password";
    private String urlKey = "url";

    private String classname = "";
    private String username = "";
    private String password = "";
    private String url = "";

    private DBUtils(){
        super();
    }

    public static DBUtils getInstance(){
        if(instance == null)
        {
            synchronized (instanceLock)
            {
                if(instance == null)
                {
                    instance = new DBUtils();
                }
                instanceLock.notifyAll();
            }
        }
        return instance;
    }

    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        PropsGetter propsGetter = PropsGetter.getInstance();
        propsGetter.init();

        classname = propsGetter.getProperty(classnameKey);
        username = propsGetter.getProperty(usernameKey);
        password = propsGetter.getProperty(passwordKey);
        url = propsGetter.getProperty(urlKey);

        Class.forName(classname).newInstance();
    }

    public void init(String classnameKey, String usernameKey,
                     String passwordKey, String urlKey) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(classnameKey != null && !classnameKey.isEmpty()
                && usernameKey != null && !usernameKey.isEmpty()
                && passwordKey != null && !passwordKey.isEmpty()
                && urlKey != null && !urlKey.isEmpty())
        {
            this.classnameKey = classnameKey;
            this.usernameKey = usernameKey;
            this.passwordKey = passwordKey;
            this.urlKey = urlKey;
        }
        init();
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        if(username != null && password != null && url != null)
        {
            conn = DriverManager.getConnection(url,username,password);
            if(conn != null && conn.isValid(0))
            {
                return conn;
            }
        }
        throw new SQLException("There was an error with the database");
    }


}
