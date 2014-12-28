package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class EnterpriseInfo {
	private String uri;
	private String uuri;
	private String name;
	private String address;//公司地址
	private String description;
	private Integer registered_capital;//注册资金
	private Integer employee_num;//雇员人数
	private String phone;
	private String main_business;//主营业务
	private String home_page;//公司主页
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRegistered_capital() {
		return registered_capital;
	}
	public void setRegistered_capital(Integer registered_capital) {
		this.registered_capital = registered_capital;
	}
	public Integer getEmployee_num() {
		return employee_num;
	}
	public void setEmployee_num(Integer employee_num) {
		this.employee_num = employee_num;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMain_business() {
		return main_business;
	}
	public void setMain_business(String main_business) {
		this.main_business = main_business;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}
	static public List<EnterpriseInfo> parseXML(String xml)
	{
		List<EnterpriseInfo> ret = new ArrayList<EnterpriseInfo>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> eiList = collection.getChildren("Uf91ef4c34394c_baoziShop_EnterpriseInfo");
	        for(int i = 0, j = eiList.size();i < j; i++)  
	        {
	        	Element eiE = eiList.get(i);
	        	Element eiUri = eiE.getChild("uri");
	        	Element eiName = eiE.getChild("name");
	        	Element eiAdd = eiE.getChild("address");
	        	Element eiDes = eiE.getChild("description");
	        	Element eiRC = eiE.getChild("registered_capital");
	        	Element eiEN = eiE.getChild("employee_num");
	        	Element eiPhone = eiE.getChild("phone");
	        	Element eiMB = eiE.getChild("main_business");
	        	Element eiHP = eiE.getChild("home_page");
	        	Element eiUUri = eiE.getChild("User").getChild("uri");
	        	EnterpriseInfo eiM = new EnterpriseInfo();
	        	eiM.setUri(eiUri.getText());
	        	eiM.setUuri(eiUUri.getText());
	        	if (eiName != null){
	        		eiM.setName(eiName.getText());
	        	}
	        	if (eiAdd != null){
	        		eiM.setAddress(eiAdd.getText());
	        	}
	        	if (eiDes != null){
	        		eiM.setDescription(eiDes.getText());
	        	}
	        	eiM.setRegistered_capital(Integer.parseInt(eiRC.getText()));
	        	eiM.setEmployee_num(Integer.parseInt(eiEN.getText()));
	        	if (eiPhone != null){
	        		eiM.setPhone(eiPhone.getText());
	        	}
	        	if (eiMB != null){
	        		eiM.setMain_business(eiMB.getText());
	        	}
	        	if (eiHP != null){
	        		eiM.setHome_page(eiHP.getText());
	        	}
	        	ret.add(eiM);
	        }
		} catch (Exception e) {
			// do nothing
		}
	    return ret;
	}
}
