<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All users are:</h1>   
        <sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/db" user="root" password=""></sql:setDataSource>
        <sql:query dataSource="${ds}" var="rs">select * from wipro;</sql:query>
        <table>
            <tr>
                <td>User ID</td>
                <td>User Name</td>
            </tr>
   <c:forEach items="${rs.rows}"  var="row">
       <tr>
           <td><c:out value="${row.id}"></c:out></td>
           <td><c:out value="${row.name}"></c:out></td>
       </tr>
   </c:forEach>
   </table>        
    </body>
</html>
