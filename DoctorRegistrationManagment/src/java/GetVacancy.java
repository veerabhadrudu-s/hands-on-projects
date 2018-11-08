import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author 275634
 */
public class GetVacancy extends HttpServlet {
    Connection Con;
    PreparedStatement PreStatement;
    public GetVacancy()
    {   try{
        Con=GetConnection.getConnection();
        PreStatement=Con.prepareStatement("Select DRM_DISTRI,DRM_OC_VA,DRM_OC_NOA,DRM_BC_VA,DRM_BC_NOA,DRM_SC_VA,DRM_SC_NOA,DRM_ST_VA,DRM_ST_NOA from DRM_Master where DRM_CATEGO=?");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");


       PrintWriter Out=response.getWriter();
       String Category=request.getParameter("category");
          if(Category.equals("all"))
          {
              response.sendRedirect("GetVacancyAll");
          }
       Date date=new Date();
       ResultSet Result = null;
       response.setContentType("text/html");
       try{
           
           synchronized(PreStatement){
           PreStatement.setString(1, Category);
            Result=PreStatement.executeQuery();
           }
           Out.println("<html>");
           Out.println("<head title=\"Vacancy Form\"> </head>");
           Out.println("<body background=\"back.jpg\">");
           Out.println("<center>");
           Out.println("<br><br><br><br><br><br><br>");
           Out.println("<table border=\"5\" >");
           Out.println("<tr><th colspan=\"9\"><center>DISTRICT WISE VACANCY POSITION AS ON "+ date.toString() +" FOR DOCTORS "+Category.toUpperCase()+" </center></th></tr>");
           Out.println("<tr>");
           Out.println("<td rowspan=\"3\"><center>District</center></td>");
           Out.println("<td colspan=\"2\"><center>OC</center></td>");
           Out.println("<td colspan=\"2\"><center>BC</center></td>");
           Out.println("<td colspan=\"2\"><center>SC</center></td>");
           Out.println("<td colspan=\"2\"><center>ST</center></td>");
           Out.println("</tr>");
           Out.println("<tr>");
           Out.println("<td><center>VA</center></td><td><center>NOA</center></td>");
           Out.println("<td><center>VA</center></td><td><center>NOA</center></td>");
           Out.println("<td><center>VA</center></td><td><center>NOA</center></td>");
           Out.println("<td><center>VA</center></td><td><center>NOA</center></td>");
           Out.println("</tr>");
           Out.println("<tr>");
           Out.println("<td colspan=\"12\"><center>VA: vacancy Available,NOA:Number of Applicants</center></td>");
           Out.println("</tr>");
           int Value[]=new int[4];
           String District;
           for(;Result.next();)
           {   District=Result.getString(1);
               Out.println("<tr>");
               Out.println("<td>");
               Out.println("<center>"+District+"</center></td>");
               Value[0]=Result.getInt(2);
               if(Value[0]>0)
               {
                   Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"Register.jsp?district="+District+"&category="+Category+"&caste=OC"+"\">"+Value[0] +"</a></center></td>");
               }
               else
               {
                   Out.println("<td bgcolor=\"#FF0000\"><center>"+Value[0]+"</center></td>");
               }
               Out.println("<td><center>"+Result.getInt(3)+"</center></td>");
               Value[1]=Result.getInt(4);
               if(Value[1]>0)
               {
                   Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"Register.jsp?district="+District+"&category="+Category+"&caste=BC"+"\">"+Value[1] +"</a></center></td>");
               }
               else
               {
                   Out.println("<td bgcolor=\"#FF0000\"><center>"+Value[1]+"</center></td>");
               }
               Out.println("<td><center>"+Result.getInt(5)+"</center></td>");
               Value[2]=Result.getInt(6);
               if(Value[2]>0)
               {
                   Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"Register.jsp?district="+District+"&category="+Category+"&caste=SC"+"\">"+Value[2] +"</a></center></td>");
               }
               else
               {
                   Out.println("<td bgcolor=\"#FF0000\"><center>"+Value[2]+"</center></td>");
               }
               Out.println("<td><center>"+Result.getInt(7)+"</center></td>");
               Value[3]=Result.getInt(8);
               if(Value[3]>0)
               {
                   Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"Register.jsp?district="+District+"&category="+Category+"&caste=ST"+"\">"+Value[3] +"</a></center></td>");
               }
               else
               {
                   Out.println("<td bgcolor=\"#FF0000\"><center>"+Value[3]+"</center></td>");
               }
               Out.println("<td><center>"+Result.getInt(9)+"</center></td>");
               Out.println("</tr>");
           }
           Out.println("</table>");
           Out.println("</center>");
           Out.println("<body>");
           Out.println("<html>");
 
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally
       {

            try
            {
                Out.close();
                Result.close();
            }
            catch (SQLException ex)
            {
            ex.printStackTrace();
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
    }// </editor-fold>
    @Override
    public void destroy()
    {   try{
        Con.close();
        PreStatement.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
