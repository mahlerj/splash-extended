<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<html>
<head>
 <title>Login</title>
</head>
<body>
<h1>
 <form:form action="captchaLoginJsp">
  <table>
   <tr>
    <td>Username: </td>
  
   <td>  Name: <input type="text" name="username"></td>
   </tr>
   <tr>
    <td>Password: </td>
    
     <td>  Password: <input type="text" name="password"></td>
   </tr>
  
   <tr>
    <td>
    	 <%
        ReCaptcha c = ReCaptchaFactory.newReCaptcha(
            "6LciewUTAAAAACjcP9xWUXYeuPi4REa6KpohnPMN",
            "6LciewUTAAAAAEU8qfsCy4gcO1MxzMqFoezv1jDk",
            false);
        out.print(c.createRecaptchaHtml(null, null));
        %>
    </td>
   </tr>
   <tr>
    <td colspan='2'>
      <input id='submit' type='submit' value='Submit' />
    </td>
   </tr>
  </table>
 </form:form>
</h1>
</body>
</html>