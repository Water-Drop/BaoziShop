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
import org.apache.commons.httpclient.methods.PostMethod;

import net.sf.json.JSONObject;

/**
 * @author Zhao Zihao
 */
@WebServlet("/AddRecruitOrder")
public class AddRecruitOrderServlet extends HttpServlet {
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
    	
    	
    	String id = (String)request.getSession().getAttribute("id");

		String url1 = "http://59.78.3.95:8080/baoziShopService/services/user/getEnterpriseInfoByUser?uuri="+id;
    	HttpClient client1 = new HttpClient();
    	GetMethod method1 = new GetMethod(url1);
    	method1.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
    	client1.executeMethod(method1);
    	String resultJson1 = method1.getResponseBodyAsString();
    	JSONObject info = JSONObject.fromObject(resultJson1);
    	
    	
        String head_count = request.getParameter("head_count");
        String candidate_count = request.getParameter("candidate_count");
        String price = "1000";
        String position = request.getParameter("position");
        String salary = request.getParameter("salary");
        String recruit_info = request.getParameter("recruit_info");
        String audit_info = request.getParameter("audit_info");
        String interview_info = request.getParameter("interview_info");
        String job_info = request.getParameter("job_info");
        String accept_count = "0";
        String location = request.getParameter("location");
        

		JSONObject member = new JSONObject();
		JSONObject json = new JSONObject();

		String url = "http://59.78.3.95:8080/baoziShopService/services/recruit/addRecruitOrder";
		
    	json.put("euri", info.get("uri"));
    	json.put("head_count", head_count);
    	json.put("candidate_count", candidate_count);
    	json.put("price", price);
    	json.put("position", position);
    	json.put("salary", salary);
    	json.put("recruit_info", recruit_info);
    	json.put("audit_info", audit_info);
    	json.put("interview_info", interview_info);
    	json.put("job_info", job_info);
    	json.put("accept_count", accept_count);
    	json.put("location", location);
    	
		
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
