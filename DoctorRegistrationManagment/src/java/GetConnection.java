/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 275634
 */
import java.sql.*;
public class GetConnection {


    public static Connection getConnection()
    {
        try{
    Class.forName("oracle.jdbc.driver.OracleDriver");
    return DriverManager.getConnection("jdbc:oracle:thin:@CTSINSRIVOR1:1521:orcl","aj025","aj025");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
