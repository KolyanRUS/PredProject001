package util;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    private static Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }

    public Connection getMySQLConnection() {
        try {
            Properties props = new Properties();
            try(InputStream in = Files.newInputStream(Paths.get("C:\\Users\\Kolyan1998\\IdeaProjects\\PredProject001\\src\\main\\resources\\database.properties"))){
                props.load(in);
            }
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            Class.forName("com.mysql.jdbc.Driver").
                    getDeclaredConstructor().
                    newInstance();//Инициализация драйвера jdbc для работы с MySQL
            return DriverManager.getConnection(url, username, password);
        } catch (Throwable t) {
            try(FileWriter writer = new FileWriter("D:\\file.txt",true)) {
                writer.write("getH2Connection() выполнить успешно не удалось, ошибка "+t.toString()+"\r\n");
                writer.flush();
            }
            catch (IOException ex) {
                //ignore
            }
        }
        return null;
    }
}