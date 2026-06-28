package servise.impl;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import dao.impl.MemberDaoImpl;
import model.entity.Games;
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

	@Override
	public Member memberByUid(String uid) {
		
		return mdi.getByUid(uid);
	}

	@Override
	public DefaultTableModel findAllMemberTable() {
		List<Member> members = mdi.getMemberAll();
		DefaultTableModel model = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row,int column) {
				return column==8|| column==9;
			}	
		};
		
		model.addColumn("編號");
		model.addColumn("密碼");
		model.addColumn("姓名");
		model.addColumn("電話/手機");
		model.addColumn("電子信箱");
		model.addColumn("年齡");
		model.addColumn("地址");
		model.addColumn("管理員");
		model.addColumn("修改");
		model.addColumn("刪除");
		
		for(Member m: members){

			String admin = m.getAdmin().equals("Y")? "是":"否";
			
			Object[] row= new Object[] {
				m.getUid(),m.getPassword(),
				m.getName(),m.getPhone(),
				m.getEmail(),
				m.getYear(),m.getAddress(),
				admin,
				"修改",
				"刪除"
			};
			model.addRow(row);
		}
		return model;
	}



}
