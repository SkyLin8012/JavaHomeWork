package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GamesDao;
import model.entity.Games;
import model.entity.Log;
import util.DbConnection;

public class GamesDaoImpl implements GamesDao {

	public static void main(String[] args) {
		GamesDao gd = new GamesDaoImpl();		
		//gd.insert(new Games(1,"狗狗","dog.png","狗狗1","2026-05-26","N",""));
		//List<Games> games = gd.getByname("貪");
		//gd.updateGames(new Games(1,"貪吃蛇","snake.png","經典的益智電子遊戲，玩家操控一條不斷前進的蛇，透過吞食畫面中的食物來增加長度，同時需避免撞擊牆壁或自己的身體","2026-06-26 12:00:00","Y","controller.SnakeGame"));
		//gd.deletGames(5);
		/*
		List<Games> games = gd.getGamesAll();
		
		for(Games o :games)
		{
			System.out.println(o.getId()+" "+o.getName()+" "+o.getPicture()+" "+o.getUrl());
		} 
		*/
	}
	Connection conn=DbConnection.getDb();

	@Override
	public void insert(Games games) {
		String sql="insert into games(name,picture,introduce,times,install,url) "
				+ "values(?,?,?,?,?,?);";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, games.getName());
			ps.setString(2, games.getPicture());
			ps.setString(3, games.getIntroduce());
			ps.setString(4, games.getTimes());
			ps.setString(5, games.getInstall());
			ps.setString(6, games.getUrl());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Games> getByname(String name) {
		String sql = "select * from games where name Like ?";
		List<Games> games = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Games ge = new Games();
				ge.setId(rs.getInt("id"));
				ge.setName(rs.getString("name"));
				ge.setPicture(rs.getString("picture"));
				ge.setTimes(rs.getString("introduce"));
				ge.setInstall(rs.getString("times"));
				ge.setTimes(rs.getString("install"));
				ge.setUrl(rs.getString("url"));
				games.add(ge);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}

	@Override
	public List<Games> getGamesAll() {
		String sql = "select * from games ";
		List<Games> games = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Games ge = new Games();
				ge.setId(rs.getInt("id"));
				ge.setName(rs.getString("name"));
				ge.setPicture(rs.getString("picture"));
				ge.setTimes(rs.getString("introduce"));
				ge.setInstall(rs.getString("times"));
				ge.setTimes(rs.getString("install"));
				ge.setUrl(rs.getString("url"));
				games.add(ge);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return games;
	}

	@Override
	public void updateGames(Games games) {
		String sql="update games set name=?,picture=?,"
				+"introduce =? ,times=?,"
				+"install=?,url=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, games.getName());
			ps.setString(2, games.getPicture());
			ps.setString(3, games.getInstall());
			ps.setString(4, games.getTimes());
			ps.setString(5, games.getInstall());
			ps.setString(6, games.getUrl());
			ps.setInt(7, games.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletGames(int id) {
		String sql="delete from games where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
