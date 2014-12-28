package model;

public class Skill {
	private String uri;
	private String name;
	private Integer level;
	private Integer higher_level;//higher level uri
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getHigher_level() {
		return higher_level;
	}
	public void setHigher_level(Integer higher_level) {
		this.higher_level = higher_level;
	}
}
