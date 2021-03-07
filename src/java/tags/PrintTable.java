
package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class PrintTable extends TagSupport{
    public int num; 
    public String color;
    public void setNum(int num) {
        this.num = num;
    }


    public void setColor(String color) {
        this.color = color;
    }
    
    
   
    @Override
    public int doStartTag() throws JspException {
        try{
            JspWriter out=pageContext.getOut();
            for(int i=1;i<=10;i++){
                out.println((i*num)+"<br>");            
            }   
        }
        catch(Exception e){
            e.printStackTrace();        
        }
        return SKIP_BODY; 
    }
    
    
    
  
}
