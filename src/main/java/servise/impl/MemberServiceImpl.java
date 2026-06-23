package servise.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import model.entity.Member;
import servise.MemeberService;

public class MemberServiceImpl implements MemeberService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	MemberDaoImpl mdi = new MemberDaoImpl();
	@Override
	public void createMember(Member member) {
		mdi.insert(member);
		
	}

	@Override
	public Member Login(String uid, String password) {
		// TODO Auto-generated method stub
		return mdi.getByUidAndPasword(uid, password);
	}

	@Override
	public boolean checkUsername(String uid) {
		boolean x=false;
		Member member= mdi.getByUid(uid);
		if(member!=null)x=true;
		return x;
	}

	@Override
	public List<Member> findAllMembers() {
		// TODO Auto-generated method stub
		return mdi.getMemberAll();
	}

	@Override
	public void update(Member member) {
		/*
		 * 1.判斷uid
		 * 2.有-->修改
		 * */
		Member temp=mdi.getByUid(member.getUid());
		if(temp!=null)
		{
			mdi.updateMember(member);
		}
		else
		{
			System.out.println("無此UID");
		}
		
	}

	@Override
	public void deleteByUid(String uid) {
		/*
		 * 1.判斷是否有此uid
		 * 2.刪除此筆資料
		 * */
		Member member= mdi.getByUid(uid);
		if(member != null)
		{
			mdi.deletMember(uid);
		}
		else
		{
			System.out.println("無此UID");
		}
		
	}



}
