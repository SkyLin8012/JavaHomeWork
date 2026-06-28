package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import model.entity.Games;
import model.entity.Member;
import servise.impl.GamesServiceImpl;
import util.TableButtonSupport;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameStoreUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private GamesServiceImpl gs = new GamesServiceImpl();
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
		//取得cookie
		Member me=null;
		me=(Member) Tool.readFile("member.txt");
		
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
		//顯示遊戲		
		table = new JTable();
		table.setFont(new Font("新細明體", Font.PLAIN, 14));
		table.setRowHeight(50);		
		scrollPane.setViewportView(table);
		
		loadPorderTable();
		//=== 將欄位變成按鈕===
		//執行遊戲
		
		table.getColumnModel().getColumn(6).setCellRenderer(
			  new TableButtonSupport(table,"執行",row->{
				 Object id = table.getValueAt(row, 0);
				 Games game= gs.findGameByid((int)id);
				 //System.out.println(game.getUrl());
				 if(game.getInstall().equals("Y"))
				 {
					 String formname=game.getUrl();
						//String formname="controller.SnakeGame";
						try {
							Class<?> clazz= Class.forName(formname);
							JFrame form = (JFrame)clazz.getDeclaredConstructor().newInstance();
							// 關鍵：設定關閉這個視窗時，只釋放該視窗資源，而不結束整個程式
							form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							form.setVisible(true);
							//dispose();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchMethodException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null,"遊戲未實裝","警告!",JOptionPane.WARNING_MESSAGE);
				 }
				
				 
			  })			
		);
		/*
		//修改
		table.getColumnModel().getColumn(7).setCellRenderer(
			  new TableButtonSupport(table,"修改",row->{
				 Object id = table.getValueAt(row, 0);
				 System.out.println("修改 ID"+id);
			  })			
		);
		//刪除
		table.getColumnModel().getColumn(8).setCellRenderer(
				  new TableButtonSupport(table,"刪除",row->{
					 Object id = table.getValueAt(row, 0);
					 System.out.println("刪除 ID"+id);
				  })			
		);*/
		//設定CellEditor，讓按鈕可點擊
		table.getColumnModel().getColumn(6).setCellEditor((TableCellEditor) table.getColumnModel().getColumn(6).getCellRenderer());
		//table.getColumnModel().getColumn(7).setCellEditor((TableCellEditor) table.getColumnModel().getColumn(7).getCellRenderer());
		//table.getColumnModel().getColumn(8).setCellEditor((TableCellEditor) table.getColumnModel().getColumn(8).getCellRenderer());
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(50);
		
		
		namemsg.setText(me.getName()+"歡迎您!!");
		
		JLabel timemsg = new JLabel("2026-06-23 21:06:11");
		timemsg.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		timemsg.setBounds(419, 10, 125, 19);
		panel.add(timemsg);
		
		JLabel lblNewLabel = new JLabel("遊樂場");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(253, 12, 77, 15);
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
				RankingList frame = new RankingList();
				// 關鍵：設定關閉這個視窗時，只釋放該視窗資源，而不結束整個程式
				//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				dispose();
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
		btnNewButton.setBounds(359, 10, 87, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("登出");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//初始化cookie
				Member temp =new Member();
				Tool.saveFile("member.txt",temp);
				LoginUI frame = new LoginUI();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(457, 10, 87, 23);
		panel_1.add(btnNewButton_2);
		//System.out.println(me.getAdmin());
		//判斷是否為管理員
		if("Y".equals(me.getAdmin()))
		{
			btnNewButton.setVisible(true);
		}else {
			btnNewButton.setVisible(false);
		}

	}
	private void loadPorderTable()
	{
		table.setModel(gs.findAllGamesTable());
	}
}
