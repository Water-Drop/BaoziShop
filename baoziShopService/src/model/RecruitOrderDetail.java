package model;

public class RecruitOrderDetail {
	private String uri;
	private String uuri;//userinfo uri
	private String ruri;//recruitorder uri
	private Integer audit_type;//�Ƿ����ͨ��
	private Integer accept_type;//�Ƿ񱻹�˾����¼ȡ
	private Integer invite_type;//�Ƿ��������
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
}
