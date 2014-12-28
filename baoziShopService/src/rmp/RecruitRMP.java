package rmp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.RecruitOrder;
import util.HttpHelper;
import util.XMLParser;

public class RecruitRMP {
	private String domain = "http://202.120.40.175:40013/RMP/Entity/Uf91ef4c34394c/";
	public Integer addRecruitOrder(RecruitOrder ro){
		XMLParser xp = new XMLParser("post");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		xp.add("set", "this.create_time", fmt.format(new Date()));
		xp.add("set", "this.head_count", ro.getHead_count() == null? "1":ro.getHead_count().toString());
		xp.add("set", "this.candidate_count", ro.getCandidate_count() == null? "1":ro.getCandidate_count().toString());
		xp.add("set", "this.price", ro.getPrice() == null?"0": ro.getPrice().toString());
		xp.add("set", "this.position", ro.getPosition());
		xp.add("set", "this.salary", ro.getSalary());
		xp.add("set", "this.recruit_info", ro.getRecruit_info());
		xp.add("set", "this.audit_info", ro.getAudit_info());
		xp.add("set", "this.interview_info", ro.getInterview_info());
		xp.add("set", "this.job_info", ro.getJob_info());
		xp.add("set", "this.head_count", ro.getAccept_count() == null? "0":ro.getAccept_count().toString());
		xp.add("set", "this.location", ro.getLocation());
		xp.add("set", "this.EnterpriseInfo", ro.getEuri());
		String xmlBody = xp.getXML();
		String url = domain + "baoziShop/RecruitOrder/";
		String resultXML = HttpHelper.SendHttpRequest("post", url, xmlBody);
		System.out.println(resultXML);
		return 0;
	}
	public List<RecruitOrder> getRecruitOrdersByEnterprise(String euri){
		List<RecruitOrder> ros = new ArrayList<RecruitOrder>();
		String url = domain + "baoziShop/RecruitOrder/?RecruitOrder.EnterpriseInfo.id=" + euri.replaceAll("baozishop/Uf91ef4c34394c_baoziShop_EnterpriseInfo/", "");
		String resultXML = HttpHelper.SendHttpRequest("get", url, null);
		ros = RecruitOrder.parseXML(resultXML);
		return ros;
	}
}
