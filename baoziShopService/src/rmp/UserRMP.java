package rmp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.EnterpriseInfo;
import model.User;
import model.UserInfo;
import util.HttpHelper;
import util.XMLParser;

public class UserRMP {
	private String domain = "http://202.120.40.175:40013/RMP/Entity/Uf91ef4c34394c/";
	public boolean isExist(String username)
	{
		boolean ret = false;
		String url = domain + "baoziShop/User/?User.username=" + username;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<User> us = User.parseXML(resultXML);
		if (us.size() > 0)
			ret = true;
		return ret;
	}
	
	public User check(String username, String password)
	{
		User ret = null;
		String url = domain + "baoziShop/User/?User.username=" + username;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<User> us = User.parseXML(resultXML);
		if (us.size() == 0)
			ret = null;
		else if (us.get(0).getPassword().equals(password))
		{
			ret = us.get(0);
			ret.setPassword("");
		}
		return ret;
	}
	
	public Integer addUser(String username, String password, String email, String type){
		if (isExist(username) || password == null || password.length() < 6 || email == null || type == null)
			return 1;
		// User
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.username", username);
		xp.add("set", "this.password", password);
		xp.add("set", "this.email", email);
		xp.add("set", "this.type", type);
		String xmlBody = xp.getXML();
		String url = domain + "baoziShop/User/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		System.out.println(resultXML);
		return 0;
	}
	
	public Integer addUserLogin(String uri, String auth_token){
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.auth_token", auth_token);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		xp.add("set", "this.create_time", fmt.format(new Date()));
		xp.add("set", "this.User", "Uf91ef4c34394c/" + uri);
		String xmlBody = xp.getXML();
		String url = domain + "baoziShop/UserLogin/";
		System.out.println(xmlBody);
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		System.out.println(resultXML);
		return 0;
	}
	
	public Integer addUserInfo(UserInfo ui){
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.name", ui.getName());
		xp.add("set", "this.gender", ui.getGender() == null?"1": ui.getGender().toString());
		xp.add("set", "this.age", ui.getAge() == null? "0" : ui.getAge().toString());
		xp.add("set", "this.description", ui.getDescription());
		xp.add("set", "this.education", ui.getEducation());
		xp.add("set", "this.graduate_school", ui.getGraduate_school());
		xp.add("set", "this.major", ui.getMajor());
		xp.add("set", "this.service_year", ui.getService_year() == null?"0":ui.getService_year().toString());
		xp.add("set", "this.salary_before", ui.getSalary_before() == null?"0":ui.getSalary_before().toString());
		xp.add("set", "this.salary_expect", ui.getSalary_expect() == null? "0":ui.getSalary_expect().toString());
		xp.add("set", "this.domain", ui.getDomain() == null? "0":ui.getDomain().toString());
		xp.add("set", "this.phone", ui.getPhone());
		xp.add("set", "this.service_experience", ui.getService_experience());
		xp.add("set", "this.expect_type", ui.getExpect_type() == null? "0":ui.getExpect_type().toString());
		xp.add("set", "this.location", ui.getLocation());
		xp.add("set", "this.head_img", ui.getHead_img());
		xp.add("set", "this.User", "Uf91ef4c34394c/" + ui.getUuri());
		String xmlBody = xp.getXML();
		String url = domain + "baoziShop/UserInfo/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		System.out.println(resultXML);
		return 0;
	}
	
	public Integer addEnterpriseInfo(EnterpriseInfo ei){
		XMLParser xp = new XMLParser("post");
		xp.add("set", "this.name", ei.getName());
		xp.add("set", "this.address", ei.getAddress());
		xp.add("set", "this.description", ei.getDescription());
		xp.add("set", "this.registered_capital", ei.getRegistered_capital() == null? "0":ei.getRegistered_capital().toString());
		xp.add("set", "this.employee_num", ei.getEmployee_num() == null? "0":ei.getEmployee_num().toString());
		xp.add("set", "this.phone", ei.getPhone());
		xp.add("set", "this.main_business", ei.getMain_business());
		xp.add("set", "this.home_page", ei.getHome_page());
		xp.add("set", "this.User", "Uf91ef4c34394c/" + ei.getUuri());
		String xmlBody = xp.getXML();
		String url = domain + "baoziShop/EnterpriseInfo/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		System.out.println(resultXML);
		return 0;
	}
	
	public UserInfo getUserInfoByUsername(String username){
		UserInfo ret = null;
		String url = domain + "baoziShop/UserInfo/?UserInfo.User.username=" + username;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<UserInfo> uis = UserInfo.parseXML(resultXML);
		if (uis.size() == 0)
			ret = null;
		else
		{
			ret = uis.get(0);
		}
		return ret;
	}
	
	public EnterpriseInfo getEnterpriseInfoByUsername(String username){
		EnterpriseInfo ret = null;
		String url = domain + "baoziShop/EnterpriseInfo/?EnterpriseInfo.User.username=" + username;
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		List<EnterpriseInfo> eis = EnterpriseInfo.parseXML(resultXML);
		if (eis.size() == 0)
			ret = null;
		else
		{
			ret = eis.get(0);
		}
		return ret;
	}
}
