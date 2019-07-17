package factory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadProperties {
    public String stringFromProperties() {
        try {
            FileInputStream fstream = new FileInputStream(getClass().getResource("/").getPath()+"\\property");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        /*switch (str) {
            case "UserDaoJDBCimpl":
                this.dao = userDAO_Factory.getUserDaoJDBCimpl();
                break;
            case "UserDaoHibernateImpl":
                this.dao = userDAO_Factory.getUserDaoHibernateImpl();
                break;
        }*/
    }
}
