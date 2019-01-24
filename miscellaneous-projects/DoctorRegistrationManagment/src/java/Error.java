import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        PrintWriter Out=response.getWriter();

        String Code=request.getParameter("code");

           if(Code.equals("1"))
           {
                  Out.println("<html><head><title> Unauthorised User</title></head>");
                  Out.println("<body background=\"unauth.jpg\"><br><br><br><br><br><br><center><h1> Unauthorised User.   </h1></center>");
                  Out.println("<body><font color=\"#FF0000\"><center><h2> The Username Or Password you entered is incorrect</h2></center></font>");
                  Out.println("<br><br><br><br><br>");
                  Out.println("<center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                  Out.println("</body>");
                  Out.println("</html>");
           }
           else if(Code.equals("2"))//all category error
           {
                  Out.println("<html><head><title> Posts are not available in the District</title></head>");
                  Out.println("<body background=\"unauth.jpg\"><br><br><br><br><br><br><font color=\"#FF0000\"><center><h1> Posts are not available in the District for your selected Category</h1></center></font>");
                  Out.println("<br><br><br><br><br>");
                  Out.println("<center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                  Out.println("</body>");
                  Out.println("</html>");
           }
           else if(Code.equals("3"))// criteria
           {     Out.println("<html><head><title> Registration Closed</title></head>");
                  Out.println("<body background=\"unauth.jpg\"><br><br><br><br><br><br><font color=\"#FF0000\"><center><h1> Registration Closed for this Category</h1></center></font>");
                  Out.println("<br><br><br><br><br>");
                  Out.println("<center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                  Out.println("</body>");
                  Out.println("</html>");
             }
        else if(Code.equals("4"))
           {     Out.println("<html><head><title>No Record available to Approve Or Reject</title></head>");
                  Out.println("<body background=\"unauth.jpg\"><br><br><br><br><br><br><font color=\"#FF0000\"><center><h1>No Record available to Approv or Reject</h1></center></font>");
                  Out.println("<br><br><br><br><br>");
                  Out.println("<center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                  Out.println("</body>");
                  Out.println("</html>");
             }
         else if(Code.equals("5"))
           {     Out.println("<html><head><title>No Record selected to Approve Or Reject</title></head>");
                  Out.println("<body background=\"unauth.jpg\"><br><br><br><br><br><br><font color=\"#FF0000\"><center><h1>You have not selected any records to Approve or Reject</h1></center></font>");
                  Out.println("<br><br><br><br><br>");
                  Out.println("<center><a href=\"index.jsp\"><image src=\"Home.png\" alt=\"Go To Home Page\" border=\"0\"/></a></center>");
                  Out.println("</body>");
                  Out.println("</html>");
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

}
