# WebApp
CRUD application using JSP and Servlet

Flow of CRUD application using JSP/Servlet 
Here using Resource annotation to avoid writing username & password of mysql's DB for purpose of security
@Resource = Tomcat server injects resource injection object & assign it to datasource
@Resource(name = "jdbc/web_student_tracker")
private DataSource datasource;
created first/welcome file as mvcservlet nothing but servlet, in init method it's checking datasource object value is null or not.
MVCservlet => written code for all the CRUD methods like liststudents,addstudents,deletestudents,updatestudents
StudentDbUtil => contains all the db related code like close connection,fetching data from table,update table
