<!--Using Java Code -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,tags.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Tracker App</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <%
        //Get the students from request object(sent by servlet)
        List<Student> thestudent=(List<Student>) request.getAttribute("Student_List");
    
     %>   
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>Shivaji University</h2>
            </div> 
        </div>
        <div id="container">
            <div id="content">
                <input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp';return false;" class="add-student-button"/>
                <table>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    <% for(Student tempStudent:thestudent) { %>
                    <tr>
                        <td> <%=tempStudent.getFirstName() %></td>
                        <td><%= tempStudent.getLastName() %></td>
                        <td><%= tempStudent.getEmail() %></td> 
                        <td>todo:add a link here</td>
                    </tr>
                    <% } %>    
                </table>      
                
            </div>
            
            
            
        </div>
    </body>
</html>
