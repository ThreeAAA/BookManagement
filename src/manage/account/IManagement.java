package manage.account;


import java.io.IOException;
import java.sql.SQLException;

public interface IManagement {
    String logins(String usernumber,String password) throws IOException, SQLException;
    String registered(String usernumber, String name, String sex, String password);

}
