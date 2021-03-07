
package tags;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class StudentDbUtil{
    private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
  
    public List<Student> getstudents() throws Exception{
        ArrayList<Student> students=new ArrayList<Student>();
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            con=dataSource.getConnection();
            String sql="select * from student";
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
              int id=rs.getInt("id");
              System.out.print("ID:"+id);
              String fname=rs.getString("first_name");
              System.out.println("First name:"+fname);
              String lname=rs.getString("last_name");
              System.out.println("Last name:"+lname);
              String email=rs.getString("email");
              System.out.println("Email:"+email);

              Student tempstudent=new Student(id,fname,lname,email);
              students.add(tempstudent);
            }//end while loop
            return students;
        }finally{
 
              close(con, stmt, rs);

        }
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	
    
   }

    void addstudent(Student tempstudent) throws SQLException {      
            try{
                Connection con=dataSource.getConnection();
                String query="insert into student(first_name,last_name,email)values(?,?,?)";
                PreparedStatement stmt=con.prepareStatement(query);
                //Statement stmt=con.createStatement();
                stmt.setString(1,tempstudent.getFirstName());
                stmt.setString(2,tempstudent.getLastName());
                stmt.setString(3,tempstudent.getEmail());
                 int count  =stmt.executeUpdate();
              System.out.println("Count:"+count);
              
                
            }catch(Exception e){
                      e.printStackTrace();
        
            }
}

    Student getStudents(String thestudentid) throws Exception {
        Student student=null;
        Connection con=null;
        PreparedStatement stmt=null;
         ResultSet rs=null;
        int studentid;
        try{
            studentid=Integer.parseInt(thestudentid);
             con=dataSource.getConnection();
            String sql="select * from student where id=?";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, studentid);
            rs=stmt.executeQuery();
            if(rs.next()){
                String fname=rs.getString("first_name");
                String lname=rs.getString("last_name");
                String email=rs.getString("email");
                student=new Student(studentid,fname,lname,email);
            }else{
                throw new Exception("Could not find studentid:"+studentid);
            }
            return student;
        }finally{
            close(con,stmt,rs);              
        }
        
    }

    void updatestudent(Student student) throws SQLException {
        Connection con=null;
        PreparedStatement stmt=null;
        try{
            con=dataSource.getConnection();
            String sql="update student set first_name=?,last_name=?,email=? where id=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1,student.getFirstName());
            stmt.setString(2,student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());
            stmt.execute();

            
        }finally{
               close(con,stmt,null);
               }
}

    void deletestudent(int studentid) throws SQLException {
        Connection con=null;
        PreparedStatement stmt=null;
        try{
            con=dataSource.getConnection();
            String sql="delete from student where id=?";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1,studentid);
            stmt.execute();         
        }finally{
            close(con,stmt,null);
            
        }
    }
}