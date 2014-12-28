package model;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class RecruitOrder {
	private String uri;
	private Date create_time;
	private Integer head_count;//招聘人数
	private Integer candidate_count;//需要的候选人人数
	private Integer price;//订单价格
	private String position;
	private String salary;//薪酬 格式：A-B
	private String recruit_info;//招聘信息，所有人可见
	private String audit_info;//审核信息，职员面试时，发送给职员
	private String interview_info;//面试信息。邀请候选人时，发送给候选人
	private String job_info;//工作信息，候选人简历提交至公司时，发送给候选人
	private Integer accept_count;//已录取的人数
	private String location;//位置
	private String euri;//enterprise info uri
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getHead_count() {
		return head_count;
	}
	public void setHead_count(Integer head_count) {
		this.head_count = head_count;
	}
	public Integer getCandidate_count() {
		return candidate_count;
	}
	public void setCandidate_count(Integer candidate_count) {
		this.candidate_count = candidate_count;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getRecruit_info() {
		return recruit_info;
	}
	public void setRecruit_info(String recruit_info) {
		this.recruit_info = recruit_info;
	}
	public String getAudit_info() {
		return audit_info;
	}
	public void setAudit_info(String audit_info) {
		this.audit_info = audit_info;
	}
	public String getInterview_info() {
		return interview_info;
	}
	public void setInterview_info(String interview_info) {
		this.interview_info = interview_info;
	}
	public String getJob_info() {
		return job_info;
	}
	public void setJob_info(String job_info) {
		this.job_info = job_info;
	}
	public Integer getAccept_count() {
		return accept_count;
	}
	public void setAccept_count(Integer accept_count) {
		this.accept_count = accept_count;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEuri() {
		return euri;
	}
	public void setEuri(String euri) {
		this.euri = euri;
	}
	static public List<RecruitOrder> parseXML(String xml)
	{
		List<RecruitOrder> ret = new ArrayList<RecruitOrder>();
		try {
			StringReader read = new StringReader(xml);
			InputSource inputSource = new InputSource(read);
			SAXBuilder builder = new SAXBuilder();
	        Document doc = builder.build(inputSource);
	        Element collection = doc.getRootElement();
	        List<Element> roList = collection.getChildren("Uf91ef4c34394c_baoziShop_RecruitOrder");
	        for(int i = 0, j = roList.size();i < j; i++)  
	        {
	        	Element roE = roList.get(i);
	        	Element roUri = roE.getChild("uri");	
	        	Element roCT = roE.getChild("creat_time");
	        	Element roHC = roE.getChild("head_count");
	        	Element roCC = roE.getChild("candidate_count");
	        	Element roPrice = roE.getChild("price");
	        	Element roP = roE.getChild("position");
	        	Element roS = roE.getChild("salary");
	        	Element roRI = roE.getChild("recruit_info");
	        	Element roAI = roE.getChild("audit_info");
	        	Element roII = roE.getChild("interview_info");
	        	Element roJI = roE.getChild("job_info");
	        	Element roAC = roE.getChild("accept_count");
	        	Element roL = roE.getChild("location");
	        	Element roEUri = roE.getChild("EnterpriseInfo").getChild("uri");
	        	RecruitOrder roM = new RecruitOrder();
	        	roM.setUri(roUri.getText());
	        	roM.setEuri(roEUri.getText());
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
	        	roM.setCreate_time(sdf.parse(roCT.getText()));
	        	roM.setHead_count(Integer.parseInt(roHC.getText()));
	        	roM.setCandidate_count(Integer.parseInt(roCC.getText()));
	        	roM.setPrice(Integer.parseInt(roPrice.getText()));
	        	if (roP != null){
	        		roM.setPosition(roP.getText());
	        	}
	        	if (roS != null){
	        		roM.setSalary(roS.getText());
	        	}
	        	if (roRI != null){
	        		roM.setRecruit_info(roRI.getText());
	        	}
	        	if (roAI != null){
	        		roM.setAudit_info(roAI.getText());
	        	}
	        	if (roII != null){
	        		roM.setInterview_info(roII.getText());
	        	}
	        	if (roJI != null){
	        		roM.setJob_info(roJI.getText());
	        	}
	        	roM.setAccept_count((Integer.parseInt(roAC.getText())));
	        	if (roL != null){
	        		roM.setLocation(roL.getText());
	        	}
	        	ret.add(roM);
	        }
		} catch (Exception e) {
			// do nothing
		}
	    return ret;
	}
}
