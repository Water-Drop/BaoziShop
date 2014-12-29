package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;

/**
 * @author Zhao Zihao
 */
@WebServlet("/Registry")
public class RegistryServlet extends HttpServlet {
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

		JSONObject member = new JSONObject();

		String url = "http://59.78.3.95:8080/baoziShopService/services/account/register?username="+username+"&password="+password+"&email="+email+"&type=1";
    	HttpClient client = new HttpClient();
    	GetMethod method = new GetMethod(url);
    	method.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
    	client.executeMethod(method);
    	String resultJson = method.getResponseBodyAsString();
    	
	    JSONObject json = JSONObject.fromObject(resultJson);
		String status = json.getString("rtn");
		if(status.equals("0")){
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
