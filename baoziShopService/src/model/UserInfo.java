package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class UserInfo {
	private String uri;
	private String uuri;
	private String name;
	private Integer gender;
	private Integer age;
	private String description;
	private String education;//学历
	private String graduate_school;//最高学历毕业学校
	private String major;//专业
	private Integer service_year;//已工作时长
	private Integer salary_before;//原工作薪酬
	private Integer salary_expect;//期望工作薪酬
	private Integer domain;//领域 服务业、金融业 etc.
	private String phone;
	private String service_experience;//工作经历
	private Integer expect_type;//是否期望跳槽
	private String location;//位置
	private String head_img;//头像路径
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUuri() {
		return uuri;
	}
	public void setUuri(String uuri) {
		this.uuri = uuri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduate_school() {
		return graduate_school;
	}
	public void setGraduate_school(String graduate_school) {
		this.graduate_school = graduate_school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getService_year() {
		return service_year;
	}
	public void setService_year(Integer service_year) {
		this.service_year = service_year;
	}
	public Integer getSalary_before() {
		return salary_before;
	}
	public void setSalary_before(Integer salary_before) {
		this.salary_before = salary_before;
	}
	public Integer getSalary_expect() {
		return salary_expect;
	}
	public void setSalary_expect(Integer salary_expect) {
		this.salary_expect = salary_expect;
	}
	public Integer getDomain() {
		return domain;
	}
	public void setDomain(Integer domain) {
		this.domain = domain;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getService_experience() {
		return service_experience;
	}
	public void setService_experience(String service_experience) {
		this.service_experience = service_experience;
	}
	public Integer getExpect_type() {
		return expect_type;
	}
	public void setExpect_type(Integer expect_type) {
		this.expect_type = expect_type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	
	static public List<UserInfo> parseXML(String xml)
	{
		List<UserInfo> ret = new ArrayList<UserInfo>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> uiList = collection.getChildren("Uf91ef4c34394c_baoziShop_UserInfo");
	        for(int i = 0, j = uiList.size();i < j; i++)  
	        {
	        	Element uiE = uiList.get(i);
	        	Element uiUri = uiE.getChild("uri");
	        	Element uiName = uiE.getChild("name");
	        	Element uiG = uiE.getChild("gender");
	         	Element uiAge = uiE.getChild("age");
	        	Element uiDes = uiE.getChild("description");
	        	Element uiEd = uiE.getChild("education");
	        	Element uiGS = uiE.getChild("graduate_school");
	        	Element uiMajor = uiE.getChild("major");
	        	Element uiSY = uiE.getChild("service_year");
	        	Element uiSB = uiE.getChild("salary_before");
	        	Element uiSaE = uiE.getChild("salary_expect");
	        	Element uiD = uiE.getChild("domain");
	        	Element uiPhone = uiE.getChild("phone");
	        	Element uiSeE = uiE.getChild("service_experience");
	        	Element uiET = uiE.getChild("expect_type");
	        	Element uiL = uiE.getChild("location");
	        	Element uiHI = uiE.getChild("head_img");
	        	Element uiUUri = uiE.getChild("User").getChild("uri");
	        	UserInfo uiM = new UserInfo();
	        	uiM.setUri(uiUri.getText());
	        	uiM.setUuri(uiUUri.getText());
	        	if (uiName != null){
	        		uiM.setName(uiName.getText());
	        	}
	        	uiM.setGender(Integer.parseInt(uiG.getText()));
	        	uiM.setAge(Integer.parseInt(uiAge.getText()));
	        	if (uiDes != null){
	        		uiM.setDescription(uiDes.getText());
	        	}
	        	if (uiEd != null){
	        		uiM.setEducation(uiEd.getText());
	        	}
	        	if (uiGS != null){
	        		uiM.setGraduate_school(uiGS.getText());
	        	}
	        	if (uiMajor != null){
	        		uiM.setMajor(uiMajor.getText());
	        	}
	        	uiM.setService_year(Integer.parseInt(uiSY.getText()));
	        	uiM.setSalary_before(Integer.parseInt(uiSB.getText()));
	        	uiM.setSalary_expect(Integer.parseInt(uiSaE.getText()));
	        	uiM.setDomain(Integer.parseInt(uiD.getText()));
	        	if (uiPhone != null){
	        		uiM.setPhone(uiPhone.getText());
	        	}
	        	
	        	if (uiSeE != null){
	        		uiM.setService_experience(uiSeE.getText());
	        	}
	        	uiM.setExpect_type(Integer.parseInt(uiET.getText()));
	        	if (uiL != null){
	        		uiM.setLocation(uiL.getText());
	        	}
	        	if (uiHI != null){
	        		uiM.setHead_img(uiHI.getText());
	        	}
	        	ret.add(uiM);
	        }
		} catch (Exception e) {
			// do nothing
		}
	    return ret;
	}
}
