package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import model.entity.Games;
import model.entity.Member;
import servise.impl.GamesServiceImpl;
import servise.impl.LogServiceImpl;
import util.Tool;
import javax.swing.JComboBox;

public class RankingList extends JFrame {

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
					RankingList frame = new RankingList();
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
	public RankingList() {
		//載入cookie
		Member me=null;
		me=(Member) Tool.readFile("member.txt");
		//提取方法
		GamesServiceImpl gs = new GamesServiceImpl();
		LogServiceImpl ls = new LogServiceImpl();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 10, 554, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel namemsg = new JLabel("");
		namemsg.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		namemsg.setBounds(10, 11, 143, 17);
		panel.add(namemsg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(10, 57, 554, 44);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(10, 111, 554, 260);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 534, 240);
		panel_2.add(scrollPane);
		//====event=====
		
		//顯示排行榜
		table = new JTable();
		table.setFont(new Font("新細明體", Font.PLAIN, 14));
		table.setRowHeight(26);		
		scrollPane.setViewportView(table);
		//寫入資料
		table.setModel(ls.findLogsTable());
		
		
		namemsg.setText(me.getName()+"歡迎您!!");
		
		JLabel timemsg = new JLabel("2026-06-23 21:06:11");
		timemsg.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timemsg.setBounds(419, 10, 125, 19);
		panel.add(timemsg);
		
		JLabel lblNewLabel = new JLabel("排行榜");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(253, 12, 77, 15);
		panel.add(lblNewLabel);
		
		//寫如遊戲名稱
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(112, 10, 90, 23);
		panel_1.add(comboBox);
		List<Games> arrygs = gs.findAllGames();
		if(arrygs!=null)
		{
			for(Games g: arrygs)
			{
				comboBox.addItem(g.getName());
			}
		}
		
		//計時器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//建立Time，每1000毫秒(1秒)執行一次
		Timer timer = new Timer(1000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				timemsg.setText(sdf.format(new Date()));
				String gameName="";
				if(comboBox.getSelectedItem()!=null)
				{
					gameName = comboBox.getSelectedItem().toString();
				}
				table.setModel(ls.findLogsTableByGName(gameName));
			}
		});
		timer.start();
		
		JButton btnNewButton_1 = new JButton("個人成就");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Achievements frame = new Achievements();
				// 關鍵：設定關閉這個視窗時，只釋放該視窗資源，而不結束整個程式
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(309, 10, 87, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1_1 = new JButton("遊戲榜");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		btnNewButton_1_1_1.setBounds(212, 10, 87, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("請選擇遊戲:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 14, 92, 15);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("回遊樂場");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameStoreUI frame = new GameStoreUI();
				frame.setVisible(true);
				dispose();
			}
			
		});
		btnNewButton.setBounds(457, 10, 87, 23);
		panel_1.add(btnNewButton);

	}
	
}
