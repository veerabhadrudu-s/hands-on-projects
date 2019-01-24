/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class GetVacancyAll extends HttpServlet {
   
    Connection Con;
    Statement[] State=new Statement[4];
    public GetVacancyAll()
    {   try{
        Con=GetConnection.getConnection();
        for(int i=0;i<=3;i++)
        State[i]=Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
         response.setHeader("Pragma","No-cache");
         response.setHeader("Cache-Control","no-cache");


         PrintWriter Out=response.getWriter();
         Date date=new Date();
         ResultSet Result[]=new ResultSet[4];
         String Category[]={"dentist","urologist","podiatrist","oncologist"};
         

         try{
               
             for(int i=0;i<=3;i++)
            {             
              Result[i]=State[i].executeQuery("Select DRM_DISTRI,DRM_OC_VA,DRM_OC_NOA,DRM_BC_VA,DRM_BC_NOA,DRM_SC_VA,DRM_SC_NOA,DRM_ST_VA,DRM_ST_NOA from DRM_Master where DRM_CATEGO='"+Category[i]+"'");
            }
             Result[0].last();
             int rowcount=Result[0].getRow();
             Result[0].beforeFirst();
             int value[][]=new int[6][8];
             for(int j=0;j<rowcount && Result[0].next() && Result[1].next()&& Result[2].next()&& Result[3].next();j++)
             {
                 for(int k=0;k<8;k++)
                 {
                     if(k==0)
                     value[j][k]=Result[0].getInt(2)+Result[1].getInt(2)+Result[2].getInt(2)+Result[3].getInt(2);
                     else if(k==1)
                     value[j][k]=Result[0].getInt(3)+Result[1].getInt(3)+Result[2].getInt(3)+Result[3].getInt(3);
                     else if(k==2)
                     value[j][k]=Result[0].getInt(4)+Result[1].getInt(4)+Result[2].getInt(4)+Result[3].getInt(4);
                     else if(k==3)
                     value[j][k]=Result[0].getInt(5)+Result[1].getInt(5)+Result[2].getInt(5)+Result[3].getInt(5);
                     else if(k==4)
                     value[j][k]=Result[0].getInt(6)+Result[1].getInt(6)+Result[2].getInt(6)+Result[3].getInt(6);
                     else if(k==5)
                     value[j][k]=Result[0].getInt(7)+Result[1].getInt(7)+Result[2].getInt(7)+Result[3].getInt(7);
                     else if(k==6)
                     value[j][k]=Result[0].getInt(8)+Result[1].getInt(8)+Result[2].getInt(8)+Result[3].getInt(8);
                     else if(k==7)
                     value[j][k]=Result[0].getInt(9)+Result[1].getInt(9)+Result[2].getInt(9)+Result[3].getInt(9);

                 }
             }
           Result[0].beforeFirst();
           Out.println("<html>");
           Out.println("<head title=\"Vacancy Form\"> </head>");
           Out.println("<body background=\"back.jpg\">");
           Out.println("<center>");
           Out.println("<br><br><br><br><br><br><br>");
           Out.println("<table border=\"5\" >");
           Out.println("<tr><th colspan=\"9\"><center>DISTRICT WISE VACANCY POSITION AS ON "+ date.toString() +" FOR DOCTORS ALL CATEGORIES</center></th></tr>");
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

              for(int i=0;i<value.length && Result[0].next();i++)
              {
               String District=Result[0].getString(1);
               Out.println("<tr>");
               Out.println("<td>");
               Out.println("<center>"+District+"</center></td>");
                  for(int j=0;j<value[i].length;j++)
                  {
                      if(j==0)
                      {
                         if(value[i][j]>0)
                         {
                             Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"RegisterAll.jsp?district="+District+"&caste=OC\">"+value[i][j]+"</a></center></td>");
                         }
                         else
                         {
                             Out.println("<td bgcolor=\"#FF0000\"><center>"+value[i][j]+"</center></td>");
                         }
                      }
                      else if(j==2)
                      {
                         if(value[i][j]>0)
                         {
                             Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"RegisterAll.jsp?district="+District+"&caste=BC\">"+value[i][j]+"</a></center></td>");
                         }
                         else
                         {
                             Out.println("<td bgcolor=\"#FF0000\"><center>"+value[i][j]+"</center></td>");
                         }
                      }
                      else if(j==4)
                      {
                         if(value[i][j]>0)
                         {
                             Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"RegisterAll.jsp?district="+District+"&caste=SC\">"+value[i][j]+"</a></center></td>");
                         }
                         else
                         {
                             Out.println("<td bgcolor=\"#FF0000\"><center>"+value[i][j]+"</center></td>");
                         }
                      }
                      else if(j==6)
                      {
                         if(value[i][j]>0)
                         {
                             Out.println("<td bgcolor=\"#00FF00\"><center><a href=\"RegisterAll.jsp?district="+District+"&caste=ST\">"+value[i][j]+"</a></center></td>");
                         }
                         else
                         {
                             Out.println("<td bgcolor=\"#FF0000\"><center>"+value[i][j]+"</center></td>");
                         }
                      }
                      else
                      {
                          Out.println("<td><center>"+value[i][j]+"<center></td>");
                      }
                  }
                Out.println("</tr>");
                  
              }

           Out.println("</table>");
           Out.println("</center>");
           Out.println("<body>");
           Out.println("<html>");

         }
         catch(Exception e)
         { e.printStackTrace();
            Out.println(e);
         }
         finally
         {
             try
             {                 
                  Out.close();
                  for(int i=0;i<=3;i++)
                  {
                    Result[i].close();
                  }
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
    }// </editor-fold>

    @Override
     public void destroy()
    {   try{
        Con.close();
        for(int i=0;i<=3;i++)
        {
           State[i].close();
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
