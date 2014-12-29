package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.EnterpriseInfo;
import model.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import rmp.UserRMP;

@Path("/user")
public class UserBiz {
	UserRMP ur = new UserRMP();
	@Path("/getUserinfoByUsername")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String getUserinfoByUser(@QueryParam("uuri")String uuri){
		Integer status = -1;
		Map<String, String> map = new HashMap<String, String>();
		try {
			UserInfo ui = ur.getUserInfoByUser(uuri);
			if (null != ui){
				status = 0;
				map.put("uri", ui.getUri());
				map.put("user_uri", ui.getUuri());
				map.put("name", ui.getName());
				map.put("gender", ui.getGender().toString());
				map.put("description", ui.getDescription());
				map.put("education", ui.getEducation());
				map.put("graduate_school", ui.getGraduate_school());
				map.put("service_year", ui.getService_year().toString());
				map.put("salary_before", ui.getSalary_before().toString());
				map.put("salary_expect", ui.getSalary_expect().toString());
				map.put("domain", ui.getDomain().toString());
				map.put("Phone", ui.getPhone());
				map.put("service_experience", ui.getService_experience());
				map.put("expect_type", ui.getExpect_type().toString());
				map.put("location", ui.getLocation());
				map.put("head_img", ui.getHead_img());
			} else {
				status = 1;//no such user info
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		map.put("status", status.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	@Path("/addUserInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String addUserInfo(@QueryParam("uuri")String uuri,@QueryParam("name")String name, @QueryParam("gender")Integer gender,
			@QueryParam("age")Integer age, @QueryParam("description")String description, @QueryParam("education")String education,
			@QueryParam("graduate_school")String graduate_school, @QueryParam("major")String major, @QueryParam("service_year")Integer service_year,
			@QueryParam("salary_before")Integer salary_before, @QueryParam("salary_expect")Integer salary_expect, @QueryParam("domain")Integer domain,
			@QueryParam("phone")String phone,@QueryParam("service_experience")String service_experience,@QueryParam("expect_type")Integer expect_type,
			@QueryParam("location")String location, @QueryParam("head_img")String head_img){
		Integer rtn = -1;
		try {
			UserInfo ui = new UserInfo();
			ui.setUuri(uuri);
			ui.setName(name);
			ui.setGender(gender);
			ui.setAge(age);
			ui.setDescription(description);
			ui.setEducation(education);
			ui.setGraduate_school(graduate_school);
			ui.setMajor(major);
			ui.setService_year(service_year);
			ui.setSalary_before(salary_before);
			ui.setSalary_expect(salary_expect);
			ui.setDomain(domain);
			ui.setPhone(phone);
			ui.setService_experience(service_experience);
			ui.setExpect_type(expect_type);
			ui.setLocation(location);
			ui.setHead_img(head_img);
			rtn = ur.addUserInfo(ui);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("rtn", rtn.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	@Path("/getEnterpriseInfoByUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String getEnterpriseInfoByUser(@QueryParam("uuri")String uuri){
		Integer status = -1;
		Map<String, String> map = new HashMap<String, String>();
		try {
			EnterpriseInfo ei = ur.getEnterpriseInfoByUser(uuri);
			if (null != ei){
				status = 0;
				map.put("uri", ei.getUri());
				map.put("user_uri", ei.getUuri());
				map.put("name", ei.getName());
				map.put("address", ei.getAddress());
				map.put("description", ei.getDescription());
				map.put("registered_capital", ei.getRegistered_capital().toString());
				map.put("employee_num", ei.getEmployee_num().toString());
				map.put("Phone", ei.getPhone());
				map.put("main_business", ei.getMain_business());
				map.put("home_page", ei.getHome_page());
			} else {
				status = 1;//no such enterprise info
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		map.put("status", status.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	@Path("/addEnterpriseInfo")
	@POST
	@Produces("text/plain;charset=utf-8")
	@Consumes("text/plain;charset=utf-8")
	public String addEnterpriseInfo(String param){
		JSONObject json_param = JSONObject.fromObject(param);
		String uuri = json_param.getString("uuri");
		String name = json_param.getString("name");
		String address = json_param.getString("address");
		String description = json_param.getString("description");
		Integer registered_capital = json_param.getInt("registered_capital");
		Integer employee_num = json_param.getInt("employee_num");
		String phone = json_param.getString("phone");
		String main_business = json_param.getString("main_business");
		String home_page = json_param.getString("home_page");
		Integer rtn = -1;
		try {
			EnterpriseInfo ei = new EnterpriseInfo();
			ei.setUuri(uuri);
			ei.setName(name);
			ei.setAddress(address);
			ei.setDescription(description);
			ei.setRegistered_capital(registered_capital);
			ei.setEmployee_num(employee_num);
			ei.setPhone(phone);
			ei.setMain_business(main_business);
			ei.setHome_page(home_page);
			if (ur.getEnterpriseInfoByUser(uuri) == null){
				rtn = ur.addEnterpriseInfo(ei);
			} else {
				rtn = ur.updateEnterpriseInfoByUser(uuri, ei);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("rtn", rtn.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	@Path("/getAllUserInfoes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserInfoes(){
		Integer status = -1;
		List<UserInfo> uis = new ArrayList<UserInfo>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			uis = ur.getAllUserInfoes();
			if (uis.size() > 0){
				status = 0;
				List<String> ui_jsons = new ArrayList<String>();
				for (int i = 0; i < uis.size(); i++){
					Map<String, String> ui_map = new HashMap<String, String>();
					ui_map.put("uri", uis.get(i).getUri());
					ui_map.put("user_uri", uis.get(i).getUuri());
					ui_map.put("name", uis.get(i).getName());
					ui_map.put("gender", uis.get(i).getGender().toString());
					ui_map.put("description", uis.get(i).getDescription());
					ui_map.put("education", uis.get(i).getEducation());
					ui_map.put("graduate_school", uis.get(i).getGraduate_school());
					ui_map.put("service_year", uis.get(i).getService_year().toString());
					ui_map.put("salary_before", uis.get(i).getSalary_before().toString());
					ui_map.put("salary_expect", uis.get(i).getSalary_expect().toString());
					ui_map.put("domain", uis.get(i).getDomain().toString());
					ui_map.put("Phone", uis.get(i).getPhone());
					ui_map.put("service_experience", uis.get(i).getService_experience());
					ui_map.put("expect_type", uis.get(i).getExpect_type().toString());
					ui_map.put("location", uis.get(i).getLocation());
					ui_map.put("head_img", uis.get(i).getHead_img());
					JSONObject ro_json = JSONObject.fromObject(ui_map);
					ui_jsons.add(ro_json.toString());
				}
				JSONArray jsonArray = JSONArray.fromObject(ui_jsons);
				map.put("UserInfoes", jsonArray.toString());
			} else {
				status = 1;//no such recruit order
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		map.put("status", status.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
