package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.logDao;
import model.entity.Log;
import util.DbConnection;

public class logDaoImpl implements logDao {

	public static void main(String[] args) {
		//new logDaoImpl().insert(new Log(1,"D0006","sky","1","貪吃蛇","10",""));
		//new logDaoImpl().updateLog("100","2026-12-01 12:00:00",1);
		//new logDaoImpl().deletLog(1);
		/*
		List<Log> logs = new logDaoImpl().getLogAll();
		for(Log o :logs)
		{
			System.out.println(o.getId()+" "+o.getGameName()+o.getMemberName()+" "+o.getTopScore());
		} 
		List<Log> logs = new logDaoImpl().getByMemberName("貓") ;
		for(Log o :logs)
		{
			System.out.println(o.getId()+" "+o.getGameName()+" "+o.getMemberName()+" "+o.getTopScore());
		} 
		*/

	}
	Connection conn = DbConnection.getDb();

	@Override
	public void insert(Log log) {
		String sql="insert into log(memberid,gameid,TopScore,times) "
				+ "values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, log.getMemberid());
			ps.setString(2, log.getGameid());
			ps.setString(3, log.getTopScore());
			ps.setString(4, log.getTimes());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Log> getLogAll() {
		String sql = "SELECT l.id, l.memberid, m.name, l.gameid,g.name,l.TopScore,l.times FROM log as l "
				+ "left join member as m on l.memberid = m.uid "
				+ "left join games as g on l.gameid = g.id;";
		List<Log> logs = new ArrayList<>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Log log = new Log();
				log.setId(rs.getInt("l.id"));
				log.setMemberid(rs.getString("l.memberid"));
				log.setMemberName(rs.getString("m.name"));
				log.setGameid(rs.getString("l.gameid"));
				log.setGameName(rs.getString("g.name"));
				log.setTopScore(rs.getString("l.TopScore"));
				log.setTimes(rs.getString("l.times"));
				
				logs.add(log);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logs;
	}

	@Override
	public List<Log> getByGameName(String name) {
		String sql="SELECT l.id, l.memberid, m.name, l.gameid,g.name,l.TopScore,l.times FROM log as l  "
				+ "left join member as m on l.memberid = m.uid "
				+ "left join games as g on l.gameid = g.id "
				+ "where g.name LIKE ? ";
		List<Log> logs = new ArrayList<>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Log log = new Log();
				log.setId(rs.getInt("l.id"));
				log.setMemberid(rs.getString("l.memberid"));
				log.setMemberName(rs.getString("m.name"));
				log.setGameid(rs.getString("l.gameid"));
				log.setGameName(rs.getString("g.name"));
				log.setTopScore(rs.getString("l.TopScore"));
				log.setTimes(rs.getString("l.times"));
				
				logs.add(log);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logs;
	}

	@Override
	public List<Log> getByMemberName(String name) {
		String sql="SELECT l.id, l.memberid, m.name, l.gameid,g.name,l.TopScore,l.times FROM log as l  "
				+ "left join member as m on l.memberid = m.uid "
				+ "left join games as g on l.gameid = g.id "
				+ "where m.name LIKE ? ";
		List<Log> logs = new ArrayList<>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Log log = new Log();
				log.setId(rs.getInt("l.id"));
				log.setMemberid(rs.getString("l.memberid"));
				log.setMemberName(rs.getString("m.name"));
				log.setGameid(rs.getString("l.gameid"));
				log.setGameName(rs.getString("g.name"));
				log.setTopScore(rs.getString("l.TopScore"));
				log.setTimes(rs.getString("l.times"));
				
				logs.add(log);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logs;
	}



	@Override
	public void updateLog(String TopScore,String times,int lid) {
		String sql ="update log set TopScore=?,times=? "
				+ "where id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, TopScore);
			ps.setString(2, times);
			ps.setInt(3,lid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletLog(int lid) {
		String sql ="delete from log where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, lid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
