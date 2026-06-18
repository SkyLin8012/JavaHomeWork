package dao;

import java.util.List;

import model.entity.Member;

public interface MemberDao {
	//create
	void insert(Member member);
	//read
	Member getByUidAndPasword(String uid,String password); //檢查長號與密碼
	Member getByUid(String uid);
	List<Member> getMemberAll();
	//update
	void updateMember(Member member);
	
	//delete
	void deletMember(String uid);
}
