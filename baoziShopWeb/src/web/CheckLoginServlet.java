package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @author Zhao Zihao
 */
@WebServlet("/CheckLogin")
public class CheckLoginServlet extends HttpServlet {
    private static final long serialVersionUID = -8312407323476917087L;
    
    @Override
    public void init() throws ServletException  {  
    }  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json; charset=utf-8");
        
		JSONObject member = new JSONObject();
		
		String id = (String)request.getSession().getAttribute("id");
		if(id != null){
			member.put("msg", 0);
		}else{
			member.put("msg", 1);
		}
		
		PrintWriter out= response.getWriter();
        out.print(member.toString());
        out.flush();
        out.close();
    }
}
