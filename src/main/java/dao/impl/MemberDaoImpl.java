package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Member;
import util.DbConnection;

public class MemberDaoImpl implements MemberDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	Connection conn =DbConnection.getDb();

	@Override
	public void insert(Member member) {
		String sql="insert into member("
	            + " uid,password,name,phone,Email,year,address)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUid());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getName());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getEmail());
			ps.setInt(6, member.getYear());
			ps.setString(7, member.getAddress());
			ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Member getByUidAndPasword(String uid, String password) {
		String sql = "select * from member where uid=? and password=?";
		Member member=null;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				member=new Member();
				member.setId(rs.getInt("id"));
				member.setUid(rs.getString("uid"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("Email"));
				member.setYear(rs.getInt("year"));
				member.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public Member getByUid(String uid) {
		String sql="select * from member where uid=? ";
		Member member=null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				member=new Member();
				member.setId(rs.getInt("id"));
				member.setUid(rs.getString("uid"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("Email"));
				member.setYear(rs.getInt("year"));
				member.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public List<Member> getMemberAll() {
		String sql="select * from member";
		List<Member> members = new ArrayList<>();
		Member member=null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				member=new Member();	
				member.setId(rs.getInt("id"));
				member.setUid(rs.getString("uid"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("Email"));
				member.setYear(rs.getInt("year"));
				member.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateMember(Member member) {
		//資料庫要關閉安全模式 SET SQL_SAFE_UPDATES = 0;
		String sql="update member set password=?,name=?,phone=?,Email=?,year=?,address=? where uid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getPassword());
			ps.setString(2, member.getName());
			ps.setString(3, member.getPhone());
			ps.setString(4, member.getEmail());
			ps.setInt(5, member.getYear());
			ps.setString(6, member.getAddress());
			ps.setString(7, member.getUid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletMember(String uid) {
		String sql = "delete from member where uid= ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	



}
