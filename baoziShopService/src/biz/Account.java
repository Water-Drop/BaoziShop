package biz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.User;
import net.sf.json.JSONObject;
import rmp.UserRMP;

@Path("/account")
public class Account {
	UserRMP ur = new UserRMP();
	@Context HttpServletRequest req; 
	
	@Path("/login")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String login(@QueryParam("username")String username, @QueryParam("password")String password){
		System.out.println(username + password);
		Integer status = -1;
		String uuri = "";
		String auth_token = "";
		try {
			User u = ur.check(username, password);
			if (null != u){
				status = 0;
				uuri = u.getUri();
				auth_token = uuri + "_" + req.getRemoteAddr();
				ur.addUserLogin(uuri, auth_token);
			} else {
				status = 1;//wrong password or username
			}
			String requestIp = req.getRemoteAddr();
			System.out.println(req.getHeader("user-agent"));
			System.out.println(requestIp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status.toString());
		map.put("uuri", uuri.toString());
		map.put("auth_token", auth_token);
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	@Path("/register")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String register(@QueryParam("username")String username, @QueryParam("password")String password,
			@QueryParam("email")String email, @QueryParam("type")String type){
		Integer rtn = -1;
		try {
			rtn = ur.addUser(username, password, email, type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("rtn", rtn.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
