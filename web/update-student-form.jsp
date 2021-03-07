<%-- 
    Document   : add-student-form
    Created on : 19 Mar, 2020, 5:28:59 PM
    Author     : Vaishnavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" type="text/css" href="CSS/add-student-style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>Shivaji University</h2>
            </div>     
        </div>  
        <div id="container">
            <h2>Update Student</h2>
            <form action="mvcservlet" method="GET">
                <input type="hidden" name="command" value="update"/>
                <input type="hidden" name="studentid" value="${Student_id.id}"/>
                <table>
                    <tbody>
                        <tr>
                            <td><label>FirstName:</label></td>
                            <td><input type="text" name="fname" value="${Student_id.firstName}"></td>
                        </tr>
                          <tr>
                            <td><label>LastName:</label></td>
                            <td><input type="text" name="lname" value="${Student_id.lastName}"></td>
                        </tr>
                          <tr>
                            <td><label>Email:</label></td>
                            <td><input type="email" name="email" value="${Student_id.email}"></td>
                        </tr>
                         <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="save" class="save"></td>
                        </tr>
                        
                    </tbody>
                </table>                
                
            </form>
            <div style="clear:both;"></div>
            <p>
                <a href="StudentControllerServlet">Back to list</a>
                
            </p>
            
        </div>
    </body>
</html>

