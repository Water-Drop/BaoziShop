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

/**
 * @author Zhao Zihao
 */
@WebServlet("/GetEnterpriseInfo")
public class GetEnterpriseInfoServlet extends HttpServlet {
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

		String url = "http://59.78.3.95:8080/baoziShopService/services/user/getEnterpriseInfoByUser?uuri="+id;
    	HttpClient client = new HttpClient();
    	GetMethod method = new GetMethod(url);
    	method.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
    	client.executeMethod(method);
    	String resultJson = method.getResponseBodyAsString();

		PrintWriter out= response.getWriter();
        out.print(resultJson);
        out.flush();
        out.close();
    }
}
