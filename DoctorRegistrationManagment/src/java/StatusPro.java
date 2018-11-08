import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
public class StatusPro extends HttpServlet {

    Connection Con;
    PreparedStatement PreState;

      public StatusPro()
      {
       try{
         Con=GetConnection.getConnection();
         PreState=Con.prepareStatement("update drm_registration set drm_name=?,drm_dob=?,drm_degree_marks=? where drm_regnumber=?");
         }
         catch(Exception e)
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
        String Name=request.getParameter("username");
        String DOB=request.getParameter("inputField");
        String Percentage=request.getParameter("percent");
        String RegNum=request.getParameter("regnum");
        SimpleDateFormat SDF=new SimpleDateFormat("dd-MMM-yyyy");        
        
        int Status;
        try
        {
            java.util.Date Date1=SDF.parse(DOB);
            long Sec=Date1.getTime();
            Date  DateOfBirth=new Date(Sec);
            PreState.setString(1, Name); 
            PreState.setDate(2, DateOfBirth);
            PreState.setInt(3, Integer.parseInt(Percentage));
            PreState.setInt(4, Integer.parseInt(RegNum));
            Status=PreState.executeUpdate();
            System.out.print(Status);
            Out.println("<html>"); 
            Out.println("<head><title> Status Updated</title><head>");
            Out.println("<body background=\"status.jpg\">");
            Out.println("<br><br><br><br><center><h1>Your Status Updated with Details</h1></center>");
            Out.println("<br><br><center><table rules=\"NONE\"><tr><td><h2>Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</h2></td><td><h2>"+Name +"</h2></td></tr>");
            Out.println("<tr><td><h2>Date Of Birth &nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;</h2></td><td><h2>"+ DateOfBirth.toString()+"</h2><td></tr>");
            Out.println("<tr><td><h2>Percentage &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</h2></td><td><h2>"+Percentage+"</h2></td></tr></table></center>");
            Out.println("<br><center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
            Out.println("</body>");
            Out.println("</html>");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request,response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    @Override
    public void destroy()
    {   try{
        Con.close();
        PreState.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
