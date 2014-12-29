package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import net.sf.json.JSONObject;

/**
 * @author Zhao Zihao
 */
@WebServlet("/AddEnterpriseInfo")
public class AddEnterpriseInfoServlet extends HttpServlet {
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
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String registered_capital = request.getParameter("registered_capital");
        String employee_num = request.getParameter("employee_num");
        String phone = request.getParameter("phone");
        String main_business = request.getParameter("main_business");
        String home_page = request.getParameter("home_page");
        
        String id = (String)request.getSession().getAttribute("id");

		JSONObject member = new JSONObject();
		JSONObject json = new JSONObject();

		String url = "http://59.78.3.95:8080/baoziShopService/services/user/addEnterpriseInfo";
		
    	json.put("uuri", id);
    	json.put("name", name);
    	json.put("address", address);
    	json.put("description", description);
    	json.put("registered_capital", registered_capital);
    	json.put("employee_num", employee_num);
    	json.put("phone", phone);
    	json.put("main_business", main_business);
    	json.put("home_page", home_page);
    	
		
		HttpClient client = new HttpClient();
    	PostMethod method = new PostMethod(url);
    	method.setRequestHeader("Content-type", "text/plain;charset=utf-8");
    	method.setRequestBody(json.toString());
    	client.executeMethod(method);
    	String resultJson = method.getResponseBodyAsString();
    	
    	System.out.print(resultJson);
    	
	    JSONObject json1 = JSONObject.fromObject(resultJson);
		String status = json1.getString("rtn");
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
