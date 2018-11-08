import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class AdminPro extends HttpServlet {

    Connection Con;
    Statement StateGet,StateSet;

    public AdminPro()
    {
        try{
            Con=GetConnection.getConnection();
            StateGet=Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            StateSet=Con.createStatement();

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

        String Type=request.getParameter("type").trim();
        PrintWriter Out=response.getWriter();
        StringBuffer SB=new StringBuffer();
        String Query=null,Category,Caste,District;
        ResultSet ResultGet=null,Resultset=null;
        int VacNum=0;int NOA=0;

        String Check[]=request.getParameterValues("check");
      

        if(Check==null)
        {
            response.sendRedirect("Error?code=5");
        }
        
          
        try{
            SB.append("select * from drm_registration where drm_regnumber in (");

                   for(int i=0;i<Check.length;i++)
                   {
                       SB.append(Check[i].trim()+",");
                   }
                  SB.deleteCharAt(SB.length()-1);
                  SB.append(")");
                  Query=SB.toString();
                  ResultGet=StateGet.executeQuery(Query);
             if( Type.equalsIgnoreCase("Approve"))
             {
                  Out.println("<html><head><title> Approved Records</title></head>");
                  Out.println("<body background=\"app.jpg\"><br><br><br><font color=\"#CC0099\"><h1> <center>Approved Records List</center></h1></font>");
                  Out.println("<center><table border=\"4\">");
                  Out.println("<tr><th><center><b>Doctor Name</b></center></th><th><center><b>Doctor Percentage</b></center></th></tr>");
                  for(;ResultGet.next();)
                  {
                      Category=ResultGet.getString(3);
                      Caste=ResultGet.getString(5);
                      District=ResultGet.getString(6);
                      Resultset=StateSet.executeQuery("select drm_"+Caste+"_va,drm_"+Caste+"_noa from drm_master where drm_catego='"+Category+"' and drm_distri='"+District+"'");
                      if(Resultset.next())
                      {
                        VacNum=Resultset.getInt(1);
                        NOA=Resultset.getInt(2);
                      }
                      if(VacNum>0)//if 2 people appling for same post and available post is 1
                      {
                          
                      StateSet.executeUpdate("update drm_master set drm_"+Caste+"_va="+(--VacNum)+", drm_"+Caste+"_noa="+(--NOA)+" where drm_catego='"+Category+"' and drm_distri='"+District+"'");
                      StateSet.executeUpdate("update drm_registration set drm_status='yes' where drm_name='"+ResultGet.getString(2)+"'");
                      
                      Out.println("<tr><td><center>"+ResultGet.getString(2)+"</center></td><td><center>"+ResultGet.getInt(8)+"</center></td><tr>");
                      }
                  }                           
                               

              }
             else if(Type.equalsIgnoreCase("Reject"))
             {
                  Out.println("<html><head><title> Rejected Records</title></head>");
                  Out.println("<body background=\"app.jpg\"><br><br><br><font color=\"#CC0099\"><h1> <center>Rejected Records List</center></h1></font>");
                  Out.println("<center><table border=\"4\">");
                  Out.println("<tr><th><center><b>Doctor Name</b></center></th><th><center><b>Doctor Percentage</b></center></th></tr>");

                  for(;ResultGet.next();)
                  {
                      Category=ResultGet.getString(3);
                      Caste=ResultGet.getString(5);
                      District=ResultGet.getString(6);
                      Resultset=StateSet.executeQuery("select drm_"+Caste+"_noa from drm_master where drm_catego='"+Category+"' and drm_distri='"+District+"'");
                      if(Resultset.next())
                      {
                           VacNum=Resultset.getInt(1);

                           
                      }
                      StateSet.executeUpdate("update drm_master set drm_"+Caste+"_noa="+(--VacNum)+" where drm_catego='"+Category+"' and drm_distri='"+District+"'");
                      Out.println("<tr><td><center>"+ResultGet.getString(2)+"</center></td><td><center>"+ResultGet.getInt(8)+"</center></td><tr>");
                      StateSet.executeUpdate("delete from drm_registration  where drm_name='"+ResultGet.getString(2)+"'");
                  }
                  
             }
                  Out.println("<tr><td colspan=\"2\"><center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center></td></tr>");
                  Out.println("</body>");
                  Out.println("</html>");


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {     try{
                  Out.close();
              ResultGet.close();
               
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
    {
        try
        {
           Con.close();
           StateGet.close();
           StateGet.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
