
package biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.RecruitOrder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import rmp.RecruitRMP;

@Path("/recruit")
public class RecruitBiz {
	RecruitRMP rr = new RecruitRMP();
	@Path("/getRecruitOrdersByEnterprise")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String getRecruitOrdersByEnterprise(@QueryParam("euri")String euri){
		Integer status = -1;
		List<RecruitOrder> ros = new ArrayList<RecruitOrder>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			ros = rr.getRecruitOrdersByEnterprise(euri);
			if (ros.size() > 0){
				status = 0;
				List<String> ro_jsons = new ArrayList<String>();
				for (int i = 0; i < ros.size(); i++){
					Map<String, String> ro_map = new HashMap<String, String>();
					ro_map.put("uri", ros.get(i).getUri());
					ro_map.put("enterprise_uri", ros.get(i).getEuri());
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					ro_map.put("create_time", fmt.format(ros.get(i).getCreate_time()));
					ro_map.put("head_count", ros.get(i).getHead_count().toString());
					ro_map.put("candidate_count", ros.get(i).getCandidate_count().toString());
					ro_map.put("price", ros.get(i).getPrice().toString());
					ro_map.put("position", ros.get(i).getPosition());
					ro_map.put("salary", ros.get(i).getSalary());
					ro_map.put("recruit_info", ros.get(i).getRecruit_info());
					ro_map.put("audit_info", ros.get(i).getAudit_info());
					ro_map.put("interview_info", ros.get(i).getInterview_info());
					ro_map.put("job_info", ros.get(i).getJob_info());
					ro_map.put("accept_count", ros.get(i).getAccept_count().toString());
					ro_map.put("location", ros.get(i).getLocation());
					JSONObject ro_json = JSONObject.fromObject(ro_map);
					ro_jsons.add(ro_json.toString());
				}
				JSONArray jsonArray = JSONArray.fromObject(ro_jsons);
				map.put("RecruitOrders", jsonArray.toString());
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
	
	@Path("/addRecruitOrder")
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public String addRecruitOrder(@QueryParam("euri")String euri,@QueryParam("head_count")Integer head_count, @QueryParam("candidate_count")Integer candidate_count,
			@QueryParam("price")Integer price, @QueryParam("position")String position, @QueryParam("salary")String salary,
			@QueryParam("graduate_school")String graduate_school, @QueryParam("major")String major, @QueryParam("service_year")Integer service_year,
			@QueryParam("recruit_info")String recruit_info, @QueryParam("audit_info")String audit_info, @QueryParam("interview_info")String interview_info,
			@QueryParam("job_info")String job_info,@QueryParam("accept_count")Integer accept_count,@QueryParam("location")String location){
		Integer rtn = -1;
		try {
			RecruitOrder ro = new RecruitOrder();
			ro.setEuri(euri);
			ro.setHead_count(head_count);
			ro.setCandidate_count(candidate_count);
			ro.setPrice(price);
			ro.setPosition(position);
			ro.setSalary(salary);
			ro.setRecruit_info(recruit_info);
			ro.setAudit_info(audit_info);
			ro.setInterview_info(interview_info);
			ro.setJob_info(job_info);
			ro.setAccept_count(accept_count);
			ro.setLocation(location);
			rtn = rr.addRecruitOrder(ro);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("rtn", rtn.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
