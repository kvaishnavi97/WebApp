/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Vaishnavi
 */
public class MVCServlet extends HttpServlet {

    private StudentDbUtil studentDbUtil;
    @Resource(name = "jdbc/web_student_tracker")
    private DataSource datasource;

    @Override
    public void init() throws ServletException {
        //To change body of generated methods, choose Tools | Templates.
        try {
            if (datasource == null) {
                throw new SQLException("cannot retrive datasource ds");

            } else {
                studentDbUtil = new StudentDbUtil(datasource);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String parameter = request.getParameter("command");
            if (parameter == null) {
                parameter = "List";
            }
            switch (parameter) {
                case "List":
                    liststudents(request, response);
                    break;
                case "ADD":
                    addstudents(request, response);
                    break;
                case "LOAD":
                    loadstudent(request, response);
                    break;
                case "update":
                    updatestudent(request, response);
                    break;
                case "DELETE":
                    deletestudent(request, response);
                    break;
                default:
                    liststudents(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(StudentControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * 
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
        processRequest(request, response);
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

        List<Student> students =studentDbUtil.getstudents();
        PrintWriter out = response.getWriter();
        out.println(students);
        request.setAttribute("Student_List", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-students1.jsp");
        dispatcher.forward(request, response);

    }

    private void addstudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //retrieve the form data
        PrintWriter out = response.getWriter();
        out.println("Inside addstudents");
        String fname = request.getParameter("fname");
        out.println(fname);
        String lname = request.getParameter("lname");
        out.println(lname);
        String email = request.getParameter("email");
        out.println(email);
        //add this data to student object                
        Student tempstudent = new Student(fname, lname, email);
        //add this student object to database
        studentDbUtil.addstudent(tempstudent);
        liststudents(request, response);
    }

    private void loadstudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student id from the data
        String thestudentid = request.getParameter("studentid");

        //get the student from database
        Student thestudent = studentDbUtil.getStudents(thestudentid);
        //place the student in request attribute
        request.setAttribute("Student_id", thestudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updatestudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student info from form data
        String studentid = request.getParameter("studentid");
        int studid = Integer.parseInt(studentid);
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");

        //create a new student object
        Student student = new Student(studid, fname, lname, email);

        //perform update on database
        studentDbUtil.updatestudent(student);
        //send them back to list-student.jsp
        liststudents(request, response);
    }

    private void deletestudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int studentid = Integer.parseInt(request.getParameter("studentid"));
        studentDbUtil.deletestudent(studentid);
        liststudents(request, response);
    }
}
