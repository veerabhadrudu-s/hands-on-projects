
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*,java.text.*"%>
<%!
Connection Con;
Statement State;
  {// instance block
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Con=DriverManager.getConnection("jdbc:oracle:thin:@CTSINSRIVOR1:1521:orcl","aj025", "aj025");
        State=Con.createStatement();

        }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
%>
  <%    response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);

        String RegNum;
        String Name;
        String Category;
        String District;
        String DateOfBirth;
        String Caste;
        String Status;
        ResultSet Result=null;
        int Percentage;
      try{
         RegNum=request.getParameter("regnumber");
         Result=State.executeQuery("select * from drm_registration where DRM_REGNUMBER="+Integer.parseInt(RegNum));
         boolean Flag=false;

         if(Result.next())
         {
             Flag=true;
         }
         if(Flag==true)
             {
             
               Name=Result.getString(2);
               Category=Result.getString(3);
               DateOfBirth=new SimpleDateFormat("dd-MMM-yyyy").format(Result.getDate(4)).toUpperCase();
               Caste=Result.getString(5);
               District=Result.getString(6);
               Status=Result.getString(7);
               Percentage=Result.getInt(8);
               Status=Status.trim();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-STORE">
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="-1">
        <title>Update Page</title>
    </head> 
    <body background="login\updateb.jpg">
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
           <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-STORE">
           <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
           <META HTTP-EQUIV="Expires" CONTENT="-1">

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>jsDatePick Javascript example</title>

        <link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.min.css" />

        <script type="text/javascript" src="jsDatePick.min.1.3.js"></script>

        <script type="text/javascript">
            window.onload = function(){
                new JsDatePick({
                    useMode:2,
                    target:"inputField",
                    dateFormat:"%d-%M-%Y"

                });
            };


        </script>
        <script type="text/javascript">
            function valid()
            {

                var Name=document.frm.username.value;
                var Date=document.frm.inputField.value;
                var Percentage=document.frm.percent.value;
                var NameVal="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ. ";
                var PercentDef="0123456789";
                var Char;
                if(Name.length==0)
                {
                    alert("Please Enter Name");
                    return false;
                }else
                    {
                     for(i=0;i<Name.length;i++)
                    {
                        Char=Name.charAt(i);
                        if(NameVal.indexOf(Char)<0)
                        {
                            alert("Special Character "+Char+" is not allowed in name");
                            document.frm.username.value="";
                            return false;

                        }
                    }
                    }
                if(Date.length==0){

                    alert("Please Enter Date Of Birth");
                        return false;
                }
                if(Percentage.length==0)
                    {
                        alert("Please Enter Percentage");
                        return false;
                    }else if(Percentage.length>2)
                        {
                            alert("Percentage Should not be more than two digits");
                            document.frm.percent.value="";
                            return false;
                        }
                        else
                            {
                                for(i=0;i<Percentage.length;i++)
                                    {
                                        Char=Percentage.charAt(i);
                                        if(PercentDef.indexOf(Char)<0)
                                            {
                                              alert("Enter valid Percentage");
                                              document.frm.percent.value="";
                                              return false;
                                            }
                                    }
                            }

                return true;
            }
        </script> 

        <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Update Form</h1>
        <%
        if(Status.equalsIgnoreCase("no"))
         {%>
           <font color="#FF2626"> <center><h2> Your Registration status is pending  </h2>  </center></font>
         <%}else
            {%>
            <font color="#0000CE"><center><h2> Your Designation is Confirmed </h2>  </center></font>
            <%}
        %>

        <table align="center" border="5" rules="none" bgcolor="#CBCD88">
            <form name="frm" action="StatusPro" method="post" onsubmit="return valid();">
                <tr>
                     <input type="hidden" name="regnum" value="<%= RegNum %>" />
                    <th><br></th>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;Name:</td>
                    <td><input type="text" name="username" value="<%=Name %>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;Category :</td>
                    <td><br><input type="text"  value="<%= Category %>" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br></td>
                </tr>
                <tr>
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;District :</td>
                    <td><br><input type="text"  value="<%= District %>" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;Caste :</td>
                    <td><br><input type="text"  value="<%= Caste %>" disabled>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;Date Of Birth :</td>
                    <td><br><input type="text"  name="inputField" value="<%=DateOfBirth %>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr> 
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;Percentage :</td>
                    <td><br><input type="text" name="percent" value="<%=Percentage %>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2"><br><center><input type="image" src="login\update.png" alt="Update" /></center><br></td>
                </tr>
            </form>
        </table>
    </body>
</html>
    <%


    }else
     {%>
           <html>
               <head>
                   <title>
                          Invalid Registation Number
                   </title>
               </head>
               <body background="unauth.jpg">
                   <br><br><br>
                   <font color="#FF0000"><center><h1> Invalid Registration Number (OR) Your Registration may be Rejected by Contractor</h1></center></font>
                   <br><br><br><br><br><br><br>
                      <center><a href="index.jsp"><image src="Home.png" alt="Go To Home Page" border="0"/></a></center>
               </body>

           </html>
 
     <%}
    }
    catch(Exception e)
    {
        e.printStackTrace();
        out.println(e);
    }
    finally
    { try{
      Result.close();
      }
      catch(Exception e)
              { e.printStackTrace();
          }
    }
    %>
    