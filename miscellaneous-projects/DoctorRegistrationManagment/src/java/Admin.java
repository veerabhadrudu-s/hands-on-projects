import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Admin extends HttpServlet {
   
        Connection Con;
        Statement State;
        Statement State2;
        
    public Admin()
    {

        try{
            Con=GetConnection.getConnection();
            State=Con.createStatement();
            State2=Con.createStatement();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 1);


        PrintWriter Out=response.getWriter();
        response.setContentType("text/html");
        String UserName=request.getParameter("username");
        String Password=request.getParameter("password");
        ResultSet Result = null,ResultData=null;
        boolean Flag=false;
        
        String PasswordDb=null;
        try{
     
        Result=State.executeQuery("select password from drm_admin where username='"+UserName+"'");
        
        for(;Result.next();)
        {
            PasswordDb=Result.getString(1);
            if(Password.equals(PasswordDb))
            {
                Flag=true;
                break;
            }
        }
         if(Flag==false)
         {
             response.sendRedirect("Error?code=1");
         }
        
        ResultData=State2.executeQuery("select * from drm_registration where drm_status='no'");

        if(!ResultData.next())
        {
            response.sendRedirect("Error?code=4");
        }
        
        
        
        Out.println("<html>");
        Out.println("<head>");
        Out.println("<title> Admin Page</title>");
        Out.println("</head>");
        Out.println("<body background=\"approving.jpg\">");
        
 
        Out.println("<h1><center>Admin Page</center></h1>");
        Out.println("<center>");
        
        Out.println("<table border=\"5\" bgcolor=\"#B3E395\" >");
        Out.println("<form  action=\"AdminPro\" method=\"post\"  ");
        Out.println("<tr><th></th>");
        Out.println("<th><center>Name</center><br></th>");
        Out.println("<th><center>Category</center><br></th>");
        Out.println("<th><center>Date Of Birth</center><br></th>");
        Out.println("<th><center>Caste</center><br></th>");
        Out.println("<th><center>District</center><br></th>");
        Out.println("<th><center>Percentage</center><br></th>");
        Out.println("<th><center>Applied Time</center><br></th>");
        Out.println("</tr>");
        do
        {
            Out.println("<tr>");
            Out.println("<td><center><input type=\"checkbox\" name=\"check\" value=\""+ResultData.getInt(1)+"\" /></center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getString(2)+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getString(3)+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getDate(4).toString()+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getString(5)+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getString(6)+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getString(8)+"&nbsp;&nbsp;&nbsp;</center></td>");
            Out.println("<td><center>&nbsp;&nbsp;&nbsp;"+ResultData.getTimestamp(9).toString()+"&nbsp;&nbsp;&nbsp;</center></td>");
           
            Out.println("</tr>");
        }while(ResultData.next());
        Out.println("<tr><td colspan=\"8\">");
        Out.println("<center><input type=\"submit\" value=\"Approve\" name=\"type\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        Out.println("<input type=\"submit\" value=\"Reject\" name=\"type\"/></center><td></tr>");
        Out.println("</form>");
        Out.println("</table>");
        
        Out.println("</center>");
        Out.println("</body>");
        Out.println("</html>");

        }
        catch(Exception e)
        {
           Out.println(e);
        }
        finally
        {
            try{
            Out.close();
            Result.close();
            ResultData.close();
            }
            catch(Exception e)
            {
               e.printStackTrace();
            }
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

     doGet(request,response);

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    @Override
    public void destroy()
    {
        try{
            Con.close();
            State.close();
            State2.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
