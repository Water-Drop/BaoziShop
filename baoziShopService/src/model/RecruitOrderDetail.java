package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class RecruitOrderDetail {
	private String uri;
	private String uuri;//userinfo uri
	private String ruri;//recruitorder uri
	private Integer audit_type;//是否审核通过
	private Integer accept_type;//是否被公司最终录取
	private Integer invite_type;//是否接受邀请
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
	public String getRuri() {
		return ruri;
	}
	public void setRuri(String ruri) {
		this.ruri = ruri;
	}
	public Integer getAudit_type() {
		return audit_type;
	}
	public void setAudit_type(Integer audit_type) {
		this.audit_type = audit_type;
	}
	public Integer getAccept_type() {
		return accept_type;
	}
	public void setAccept_type(Integer accept_type) {
		this.accept_type = accept_type;
	}
	public Integer getInvite_type() {
		return invite_type;
	}
	public void setInvite_type(Integer invite_type) {
		this.invite_type = invite_type;
	}
	static public List<RecruitOrderDetail> parseXML(String xml)
	{
		List<RecruitOrderDetail> ret = new ArrayList<RecruitOrderDetail>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> rodList = collection.getChildren("Uf91ef4c34394c_baoziShop_RecruitOrderDetail");
	        for(int i = 0, j = rodList.size();i < j; i++)  
	        {
	        	Element rodE = rodList.get(i);
	        	Element rodUri = rodE.getChild("uri");	
	        	Element rodAuT = rodE.getChild("audit_type");
	        	Element rodAcT = rodE.getChild("accept_type");
	        	Element rodIT = rodE.getChild("invite_type");
	        	Element rodUUri = rodE.getChild("UserInfo").getChild("uri");
	          	Element rodRUri = rodE.getChild("RecruitOrder").getChild("uri");
	        	RecruitOrderDetail rodM = new RecruitOrderDetail();
	        	rodM.setUri(rodUri.getText());
	        	rodM.setUuri(rodUUri.getText());
	        	rodM.setRuri(rodRUri.getText());
	        	rodM.setAudit_type(Integer.parseInt(rodAuT.getText()));
	        	rodM.setAccept_type(Integer.parseInt(rodAcT.getText()));
	        	rodM.setInvite_type(Integer.parseInt(rodIT.getText()));
	        	ret.add(rodM);
	        }
		} catch (Exception e) {
			// do nothing
		}
	    return ret;
	}
}
