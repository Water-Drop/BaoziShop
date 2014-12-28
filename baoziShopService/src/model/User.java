package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class User {
	private String uri;
	private String username;
	private String password;
	private String email;
	private Integer type;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	static public List<User> parseXML(String xml)
	{
		List<User> ret = new ArrayList<User>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> userList = collection.getChildren("Uf91ef4c34394c_baoziShop_User");
	        for(int i = 0, j = userList.size();i < j; i++)  
	        {
	        	Element userE = userList.get(i);
	        	Element userUri = userE.getChild("uri");
	        	Element userName = userE.getChild("username");
	        	Element userPW = userE.getChild("password");
	        	Element userMail = userE.getChild("email");
	        	Element userType = userE.getChild("type");
	        	User userM = new User();
	        	userM.setUri(userUri.getText());
	        	userM.setUsername(userName.getText());
	        	userM.setPassword(userPW.getText());
	        	if (userMail != null){
	        		userM.setEmail(userMail.getText());
	        	}
	        	userM.setType(Integer.parseInt(userType.getText()));
	        	ret.add(userM);
	        }
		} catch (Exception e) {
			// do nothing
		}
        return ret;
	}
}
