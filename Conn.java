import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;


    public Conn(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        c= DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "shranya");
        s=c.createStatement();
        System.out.println("connected");
    }catch(Exception e){
        System.out.println(e);
    }
    }
}
