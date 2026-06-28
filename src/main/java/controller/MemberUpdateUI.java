package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.entity.Member;
import servise.MemeberService;
import servise.impl.MemberServiceImpl;
import util.Tool;

public class MemberUpdateUI extends JFrame {

	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private JTextField uid;
	private JTextField password;
	private JTextField name;
	private JTextField phone;
	private JTextField email;
	private JTextField year;
	private String Uid;
	private boolean checkadmin = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberUpdateUI frame = new MemberUpdateUI("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberUpdateUI(String Uid) {
		//載入cookie
				Member me=null;
				me=(Member) Tool.readFile("member.txt");
		//載入傳過來的Uid
		this.Uid = Uid;
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 457, 429);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(192, 192, 192));
				panel.setBounds(0, 0, 441, 42);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel_7 = new JLabel("會員修正");
				lblNewLabel_7.setFont(new Font("微軟正黑體", Font.BOLD, 16));
				lblNewLabel_7.setBounds(190, 10, 71, 22);
				panel.add(lblNewLabel_7);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(192, 192, 192));
				panel_1.setBounds(0, 51, 441, 339);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
				uid = new JTextField();
				uid.setBounds(118, 10, 227, 21);
				panel_1.add(uid);
				uid.setColumns(10);
				
				password = new JTextField();
				password.setBounds(118, 41, 227, 21);
				panel_1.add(password);
				password.setColumns(10);
				
				name = new JTextField();
				name.setBounds(118, 97, 227, 21);
				panel_1.add(name);
				name.setColumns(10);
				
				phone = new JTextField();
				phone.setBounds(118, 128, 227, 21);
				panel_1.add(phone);
				phone.setColumns(10);
				
				email = new JTextField();
				email.setBounds(118, 159, 227, 21);
				panel_1.add(email);
				email.setColumns(10);
				
				year = new JTextField();
				year.setBounds(118, 190, 227, 21);
				panel_1.add(year);
				year.setColumns(10);				

				JTextArea address = new JTextArea();
				address.setBounds(118, 221, 227, 75);
				panel_1.add(address);
				
				JLabel lblNewLabel = new JLabel("會員ID");
				lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel.setBounds(41, 13, 67, 18);
				panel_1.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("會員密碼");
				lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_1.setBounds(41, 44, 67, 18);
				panel_1.add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("姓名");
				lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_2.setBounds(41, 100, 46, 15);
				panel_1.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("電話");
				lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_3.setBounds(41, 131, 46, 15);
				panel_1.add(lblNewLabel_3);
				
				JLabel lblNewLabel_4 = new JLabel("電子郵件");
				lblNewLabel_4.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_4.setBounds(41, 162, 67, 18);
				panel_1.add(lblNewLabel_4);
				
				JLabel lblNewLabel_5 = new JLabel("年齡");
				lblNewLabel_5.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_5.setBounds(41, 193, 67, 18);
				panel_1.add(lblNewLabel_5);
				
				JLabel lblNewLabel_6 = new JLabel("地址");
				lblNewLabel_6.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_6.setBounds(41, 221, 46, 21);
				panel_1.add(lblNewLabel_6);
				
				JCheckBox admin = new JCheckBox("是");
				admin.setBounds(118, 68, 97, 23);
				panel_1.add(admin);
				
				JLabel lblNewLabel_1_1 = new JLabel("管理員帳號");
				lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
				lblNewLabel_1_1.setBounds(41, 72, 71, 18);
				panel_1.add(lblNewLabel_1_1);
				
				//===========event==============
				//環境設定	
				uid.setEditable(false);
				MemberServiceImpl ms = new MemberServiceImpl();
				Member mefild = ms.memberByUid(Uid);
				if(mefild!=null)
				{
					uid.setText(mefild.getUid());
					password.setText(mefild.getPassword());					
					name.setText(mefild.getName());
					phone.setText(mefild.getPhone());
					email.setText(mefild.getEmail());
					year.setText(Integer.toString(mefild.getYear()));
					address.setText(mefild.getAddress());
					if(mefild.getAdmin().equals("Y"))
					{
						admin.setSelected(true);// 預設勾選（這行不寫也是預設不勾選）
					}else {
						admin.setSelected(false); 
					}					
				}
				
				//清空
				JButton clean = new JButton("清空");
				clean.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						uid.setText("");
						password.setText("");
						name.setText("");
						phone.setText("");
						email.setText("");
						year.setText("");
						address.setText("");
						admin.setSelected(false); // 預設不勾選（這行不寫也是預設不勾選）
					}
				});
				clean.setBounds(258, 306, 87, 23);
				panel_1.add(clean);
				
				
				JButton register = new JButton("修正");
				register.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(password.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"密碼不能為空","警告!",JOptionPane.WARNING_MESSAGE);	
						}
						else if(phone.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"電話不能為空","警告!",JOptionPane.WARNING_MESSAGE);		
						}
						else if(email.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null,"電子郵件不能為空","警告!",JOptionPane.WARNING_MESSAGE);		
						}
						else
						{				
								Member member=new Member();
								member.setUid(uid.getText());
								member.setPassword(password.getText());
								member.setName(name.getText());
								member.setPhone(phone.getText());						
								member.setEmail(email.getText());
								if (year.getText().trim().equals("")) {
									member.setYear(1);
							    } else {
							    	member.setYear(Integer.parseInt(year.getText()));
							    }
								member.setYear(Integer.parseInt(year.getText()));
								member.setAddress(address.getText());
								String Admin="";
								if(admin.isSelected()) {Admin="Y";}
								else {Admin="N";}
								member.setAdmin(Admin);
								//System.out.println(member.getEmail());
								ms.update(member);								
								JOptionPane.showMessageDialog(null,"會員修改成功!!","修改成功!",JOptionPane.INFORMATION_MESSAGE);						
								dispose();								
							
						}
						
					}
				});
				register.setBounds(118, 306, 87, 23);
				panel_1.add(register);
				

	}

}
