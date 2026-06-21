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

public class GameStoreUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
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
		namemsg.setBounds(127, 10, 173, 17);
		panel.add(namemsg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(10, 57, 414, 194);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//====event=====
		namemsg.setText(me.getName()+"歡迎您!!");

	}

}
