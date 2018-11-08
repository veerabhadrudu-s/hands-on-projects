
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class TimeStamp extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
         try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection Con=DriverManager.getConnection("jdbc:oracle:thin:@CTSINSRIVOR1:1521:orcl","aj025","aj025");
        java.util.Date d=new java.util.Date();
        pw.println(d+"<br>");
        Timestamp time=new Timestamp(d.getTime());
        pw.println(time);
       
        PreparedStatement ST=Con.prepareStatement("insert into table3 values(?)");
        ST.setTimestamp(1, time);
        int status=ST.executeUpdate();
        pw.println("<br> hai <br>");
        PreparedStatement STD=Con.prepareStatement("select * from table3");
        ResultSet rs=STD.executeQuery();
        for(;rs.next();)
        {
            pw.println(rs.getTimestamp(1).toString());
        }
        pw.println(status);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            pw.println(e);
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
