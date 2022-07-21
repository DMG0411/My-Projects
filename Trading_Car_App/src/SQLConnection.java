import javax.naming.spi.DirStateFactory;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnection {
    Connection conn = null;
    Statement st = null;
    SQLConnection(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql:///carstocks","root","password");
            st = (Statement)conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
