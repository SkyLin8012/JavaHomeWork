package model.entity;

public class Log {
	private int id;
	private String memberid;
	private String memberName;
	private String gameid;
	private String gameName;
	private String TopScore;
	private String times;
	public Log(){}
	public Log(int id, String memberid, String memberName, String gameid, String gameName, String topScore,
			String times) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.memberName = memberName;
		this.gameid = gameid;
		this.gameName = gameName;
		TopScore = topScore;
		this.times = times;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getTopScore() {
		return TopScore;
	}
	public void setTopScore(String topScore) {
		TopScore = topScore;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
	
}
