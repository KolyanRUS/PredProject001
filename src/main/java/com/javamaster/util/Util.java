package com.javamaster.util;

import com.javamaster.model.User;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
@Component
public class Util {
    private static final String hibernate_show_sql = "true";
    //в консоль будут выводиться SQL-запросы, которые скрыты за Hibernate-кодом


    private static final String hibernate_hbm2ddl_auto = "create";
    //// свойство, которое указывается что нужно сделать со схемой БД при инициализации
    //каждый раз при запуске приложения, схема БД будет создаваться наново.
    //Все данные, которые были занесены раньше, будут удалены

    private static Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }

    public Connection getMySQLConnection() {
        try {
            Properties props = new Properties();
            /*try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("database.properties")))) {
                String str;
                //getClass().getClassLoader().getResourceAsStream("database.properties");
                System.out.println("---===---===---===");//getClass().getResource("/").getPath()+"\\database.properties"
                while((str = br.readLine())!=null) {//getResourceAssStream
                    System.out.println(str);//
                }
                System.out.println("---===---===---===");
            }*/
            try(InputStream in = getClass().getClassLoader().getResourceAsStream("database.properties")){
                props.load(in);
            }
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            Class.forName("com.mysql.jdbc.Driver");//Инициализация драйвера jdbc для работы с MySQL
            return DriverManager.getConnection(url, username, password);
        } catch (Throwable t) {
            System.out.println("getMySQLConnection() выполнить успешно не удалось, ошибка "+t.toString());
        }
        return null;
    }
    public Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/Users");
        configuration.setProperty("hibernate.connection.username", "Kolyan1998");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }
}