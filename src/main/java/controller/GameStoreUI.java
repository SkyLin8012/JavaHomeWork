package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.entity.Member;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameStoreUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameStoreUI frame = new GameStoreUI();
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
	public GameStoreUI() {
		Member me=null;
		me=(Member) Tool.readFile("member.txt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 10, 414, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel namemsg = new JLabel("");
		namemsg.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		namemsg.setBounds(10, 11, 143, 17);
		panel.add(namemsg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(10, 57, 414, 44);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(10, 111, 414, 260);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 10, 394, 240);
		panel_2.add(table);
		
		//====event=====
		namemsg.setText(me.getName()+"歡迎您!!");
		
		JLabel timemsg = new JLabel("2026-06-23 21:06:11");
		timemsg.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timemsg.setBounds(279, 11, 125, 19);
		panel.add(timemsg);
		
		JLabel lblNewLabel = new JLabel("遊樂場");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(181, 12, 77, 15);
		panel.add(lblNewLabel);
		
		//計時器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//建立Time，每1000毫秒(1秒)執行一次
		Timer timer = new Timer(1000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timemsg.setText(sdf.format(new Date()));
			}
		});
		timer.start();
		
		JButton btnNewButton_1 = new JButton("排行榜");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 10, 87, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("會員管理");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberUI form = new MemberUI();
				form.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(317, 10, 87, 23);
		panel_1.add(btnNewButton);
		System.out.println(me.getAdmin());
		//判斷是否為管理員
		if("Y".equals(me.getAdmin()))
		{
			btnNewButton.setVisible(true);
		}else {
			btnNewButton.setVisible(false);
		}

	}
}
