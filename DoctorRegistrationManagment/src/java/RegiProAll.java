import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;

public class RegiProAll extends HttpServlet {

    Connection Con;
     PreparedStatement PreState;
     Statement State;
     public RegiProAll()
     {   try{
         Con=GetConnection.getConnection();
         PreState=Con.prepareStatement("insert into drm_registration values(?,?,?,?,?,?,?,?,?)");
         State=Con.createStatement();
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        PrintWriter Out=response.getWriter();
        ResultSet ResultSequence=null,ResultValue=null;
        int Detail[]=new int[2];
        String Name=request.getParameter("username");
        String Category=request.getParameter("category");
        String Caste=request.getParameter("caste");
        String District=request.getParameter("district");
        String DOB=request.getParameter("inputField");
        String Percentage=request.getParameter("percent");
        SimpleDateFormat SDF=new SimpleDateFormat("dd-MMM-yyyy");
        
        try{
            ResultValue=State.executeQuery("select DRM_"+Caste+"_VA,DRM_"+Caste+"_NOA from DRM_MASTER where drm_catego='"+Category+"' and drm_distri='"+District+"'");
        for(;ResultValue.next();)
        {
            Detail[0]=ResultValue.getInt(1);
            Detail[1]=ResultValue.getInt(2);
        }
        if(Detail[0]==0)
         {
            response.sendRedirect("Error?code=2");
         }

            if(10*Detail[0]>Detail[1])
            {
                     java.util.Date date=SDF.parse(DOB);
                     long Sec=date.getTime();
                     java.sql.Date DateOfBirth=new java.sql.Date(Sec);
                     ResultSequence=State.executeQuery("select DRM_REGSEQ.NEXTVAL from dual");
                     int seq=0;
                     if(ResultSequence.next())
                     {
                        seq=ResultSequence.getInt(1);
            
                     }

                      PreState.setInt(1, seq);
                      PreState.setString(2, Name);
                      PreState.setString(3, Category);
                      PreState.setDate(4, DateOfBirth);
                      PreState.setString(5, Caste);
                      PreState.setString(6, District);
                      PreState.setString(7, "no");
                      PreState.setInt(8, Integer.parseInt(Percentage));
                      PreState.setTimestamp(9,new java.sql.Timestamp(new java.util.Date().getTime()));
                      int Status=PreState.executeUpdate();
                      System.out.print(Status);
                      State.executeUpdate("update drm_master set drm_"+Caste+"_noa="+(++Detail[1])+"where drm_catego='"+Category+"' and drm_distri='"+District+"'");
                      Out.println("<html><head><title> Successfully Registered</title></head>");
                      Out.println("<body background=\"reg.jpg\"><br><br><br><font color=\"BC2EBC\"><center><h1> Successfully Registered</h1></center></font>");
                      Out.println("<br><br><br><br><br><br><br><br><br><br>");
                      Out.println("<h3><center>Please notedown Your Registration Number for Further Requests:</h3><h2>"+seq+"</h2><br><br><br><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                      Out.println("</body>");
                      Out.println("</html>");
            }//inner
            else
            {

                response.sendRedirect("Error?code=3");

            }
       
        
        }
        catch(Exception e)
        {
            Out.println(e);
            e.printStackTrace();
        }
        finally
        {
            try{
                Out.close();
                ResultSequence.close();
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
        PreState.close();
        State.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
