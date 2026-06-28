package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import servise.MemeberService;
import servise.impl.MemberServiceImpl;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberDelete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String Uid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDelete frame = new MemberDelete("");
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
	public MemberDelete(String Uid) {
		
		//載入方法
		MemeberService ms = (MemeberService) new MemberServiceImpl();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("會員XXX是否要刪除?");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(75, 22, 162, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel.setText("會員ID:"+Uid+" 是否要刪除?");
		
		JButton btnNewButton = new JButton("是");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ms.deleteByUid((String)Uid);
				dispose();
			}
		});
		btnNewButton.setBounds(45, 61, 87, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("否");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(169, 61, 87, 23);
		contentPane.add(btnNewButton_1);

	}
}
