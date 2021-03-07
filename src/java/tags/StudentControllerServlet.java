package tags;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class StudentControllerServlet extends HttpServlet {

      //Tomcat server injects resource injection object and assign it to the datasource 
      private StudentDbUtil studentDbUtil;
     @Resource(name="jdbc/web_student_tracker")
     private DataSource datasource;

    @Override
    public void init() throws ServletException {
        //To change body of generated methods, choose Tools | Templates.
        try{
            if(datasource==null){
                throw new SQLException("cannot retrive datasource ds");
                
            }else{
            studentDbUtil=new StudentDbUtil(datasource);
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
     
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           PrintWriter out=response.getWriter();
            out.println("students");
        
          try {
              out.println("students 2");
              liststudents(request,response);
          } catch (Exception ex) {
              Logger.getLogger(StudentControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void liststudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
            List<Student> students=studentDbUtil.getstudents();
            PrintWriter out=response.getWriter();
            out.println(students);
            request.setAttribute("Student_List",students);
            RequestDispatcher dispatcher=request.getRequestDispatcher("list-students1.jsp");
            dispatcher.forward(request, response);
            
            
    }

}
