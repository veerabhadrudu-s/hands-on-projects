
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
String District=request.getParameter("district");
String Caste=request.getParameter("caste");
String Category=request.getParameter("category");
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body background="login\updateb.jpg">
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>

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
        <br><br>
        <font color=\"#0000FF\"><h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Registration Form</h1></font>
        
        <table align="center" border="5" rules="none" bgcolor="#99FFFF">
            <form name="frm" action="RegiPro" method="get" onsubmit="return valid();">
                <tr>
                     <input type="hidden" name="category" value="<%= Category %>" />
                     <input type="hidden" name="district" value="<%= District %>" />
                     <input type="hidden" name="caste" value="<%= Caste %>" />
                    <th colspan="2"><br></th>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;Name:</td>
                    <td><input type="text" name="username" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
                    <td><br><input type="text"  name="inputField" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td><br>&nbsp;&nbsp;&nbsp;&nbsp;Percentage :</td>
                    <td><br><input type="text" name="percent" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr> 
                    <td colspan="2"><br><br><center><input type="image" src="login\regist.png" alt="Register" /></center><br><br></td>
                </tr>
            </form>
        </table> 
    </body>
</html>
