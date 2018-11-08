<%-- 
    Document   : index
    Created on : May 31, 2011, 4:10:10 PM
    Author     : 275634
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%
   response.setHeader("Pragma","No-cache");
   response.setHeader("Cache-Control","no-cache");

   %>

<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Registration Managment</title>
    </head>
    <body background="login\c1.png">
        <script type="text/javascript">
            function valid()
            {
                var Name=document.admin.username.value;
                var Password=document.admin.password.value;
                var NameVal="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ. ";
                var PasswordVal="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.0123456789 !@$#%*";
                var Char;
                if(Name.length==0)
                    {
                        alert("Please Enter Username ");
                        return false;
                    }
                    else
                        {
                            for(i=0;i<=Name.length;i++)
                                {
                                    Char=Name.charAt(i);
                                    if(NameVal.indexOf(Char)<0)
                                        {
                                            alert("Special Characters are not allowed in Username");
                                            document.admin.username.value="";
                                            return false;
                                        }
                                }
                        }
                     if(Password.length==0)
                         {
                             alert("Password cannot be empty");
                             return false;
                         }
                         else
                         {
                             for(i=0;i<Password.length;i++)
                                 {
                                     Char=Password.charAt(i);

                                          if(PasswordVal.indexOf(Char)<0)
                                         {
                                             alert("Invalid character in Password");
                                             document.admin.password.value="";
                                             return false;
                                         }
                                 }
                         }
                         
                return true;
            }
</script>
<script type="text/javascript">
            function Cal()
            {
                
                 var Status=document.check.regnumber.value;
                 var StatusVal="0123456789";
                 
                             if(Status.length==0)
                             {
                                 alert("Status number can't be empty");
                                 return false;
                             }
                             else if(Status.length>4)
                                 {
                                    alert("Invalid Status Code length");
                                    document.check.regnumber.value="";
                                    return false;
                                 }
                             else
                              {
                                  for(i=0;i<Status.length;i++)
                                      {
                                          Char=Status.charAt(i);
                                          if(StatusVal.indexOf(Char)<0)
                                              {
                                                  alert("Only numbers allowed in the status code");
                                                  document.check.regnumber.value="";
                                                  return false;
                                              }
                                      }
                              }
                return true;
            }

        </script> 
     
     <center><br><br><br><br><table rules="NONE" frame="VOID">
         <tr><br>
           <td rowspan="2"><br><br><br><center> <b> NEW REGISTRATION </b><br></center>
                 <form action="GetVacancy" method="post">
                <center><pre><b>Select Category </b> <select name="category">
                 <option value="dentist">DENTIST</option>
                 <option value="urologist">UROLOGIST</option>
                 <option value="podiatrist">PODIATRIST</option>
                 <option value="oncologist">ONCOLOGIST</option>
                 <option value="all">ALL CATEGORIES</option>
                </select></pre></center>
                 <pre>    <input type="image" src="login\vacancy_1.png" alt="Get Vacancy"/></pre>
             </form>
           </td>
           <td >
               <center> <b>ADMIN LOGIN</b>
               <form name="admin" action="Admin" method="post" onsubmit="return valid()" >
                 <center><pre> UserName : <input type="text" name="username" size="15"></pre>
                  <pre> Password : <input type="password" name="password" size="17"></pre>
                  <pre>  <input type="image" src="login\login2.png" alt="login"/></pre>
                 </center>
               </form>
               </center>
           </td>
         </tr>
         <tr>
             <td><br><br><br><br>
             <b>CHECK STATUS</b><br>
             <form name="check" action="Status.jsp" method="post" onsubmit="return Cal()" ><center>Reg Num:
                  <input type="text" name="regnumber" />
                  <pre>     <input type="image" src="login\Check.png" alt="Check Status" /></pre></center>
             </form>

             </td>
         </tr>

     </table>
     </center>
    </body>
</html>
