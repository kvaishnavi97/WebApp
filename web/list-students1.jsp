<!--Using JSTL Tags      -->
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
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>Shivaji University</h2> 
            </div> 
        </div>
        <div id="container">
            <div id="content">
                <input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button"/>
                <table>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="tempStudent" items="${Student_List}">
                        <!--set a link to update a student-->
                          <c:url  var="tempLink" value="mvcservlet">
                                    <c:param name="command" value="LOAD"/>
                                    <c:param name="studentid" value="${tempStudent.id}"/>
                          </c:url>
                        <!-- set a link to delete a student-->
                         <c:url  var="deleteLink" value="mvcservlet">
                                    <c:param name="command" value="DELETE"/>
                                    <c:param name="studentid" value="${tempStudent.id}"/>
                          </c:url>
                        <tr>
                            <td><c:out value="${tempStudent.firstName}"></c:out></td>
                             <td><c:out value="${tempStudent.lastName}"></c:out></td>
                             <td><c:out value="${tempStudent.email}"></c:out></td>    
                             <td><a href="${tempLink}">Update</a>
                                 |
                                 <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false" > Delete</a>
                             </td>
                       </tr>              
                    </c:forEach>
                </table>      
                
            </div>
           
            
        </div>
    </body>
</html>
