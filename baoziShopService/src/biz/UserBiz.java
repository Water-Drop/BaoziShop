package biz;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.UserInfo;
import net.sf.json.JSONObject;
import rmp.UserRMP;

@Path("/user")
public class UserBiz {
	UserRMP ur = new UserRMP();
	@Path("/getUserinfoByUsername")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String getUserinfoByUsername(@QueryParam("username")String username){
		Integer status = -1;
		Map<String, String> map = new HashMap<String, String>();
		try {
			UserInfo ui = ur.getUserInfoByUsername(username);
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
				status = 1;//no such userinfo
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		map.put("status", status.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	@Path("/addUserInfo")
	@POST
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
}
