package tags;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Vaishnavi
 */
public class Mytaghandler extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        
        try{
           //task done by tag
            HttpServletResponse res;
            //PrintWriter out=res.getWriter();
            JspWriter out=pageContext.getOut();
            out.println("<h1>This is my custom tag</h1>");
            
            
        }catch(Exception e){
            
           e.printStackTrace();
        }
        return SKIP_BODY; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
