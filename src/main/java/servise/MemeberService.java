package servise;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.entity.Member;

public interface MemeberService {
	//create
		//註冊,新增
		void createMember(Member member);
	//read
		//登入判斷
		Member Login(String uid,String password);
		//檢查帳號重複
		boolean checkUsername(String uid);
		//搜尋全部
		List<Member> findAllMembers();
		//取得特定人員資料
		Member memberByUid(String uid);
		//table
		DefaultTableModel findAllMemberTable();
		
	//update
		void update(Member member);
	//delete
		void deleteByUid(String uid);

}
