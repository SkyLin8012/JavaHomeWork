package model.entity;

public class Games {
	private int id;
	private String name;
	private String picture;
	private String introduce;
	private String times; 
	private String install;
	public Games() {};
	public Games(int id, String name, String picture, String introduce, String times, String install) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.introduce = introduce;
		this.times = times;
		this.install = install;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getInstall() {
		return install;
	}
	public void setInstall(String install) {
		this.install = install;
	}
	
	

}
