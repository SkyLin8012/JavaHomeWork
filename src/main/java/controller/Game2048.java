package controller;

import java.awt.event.ActionListener;//動作監聽器介面，用於接收並處理ActionEvent
import java.sql.Time;//計時工具
import java.util.Random; //亂數產生器

import javax.sound.sampled.Clip;//引訊剪輯物件，可把音樂檔反覆播放
import javax.swing.ImageIcon;
import javax.swing.JFrame;//視窗框架元件，用於建立桌面應用程式主視窗
import javax.swing.JPanel; //引入面板元件，用於建立自訂的遊戲畫布
import javax.swing.SwingUtilities; 
//引入執行緒工具，確保圖形介面於安全的事件衍生執行緒中啟動

public class Game2048 extends JFrame {
	
	public Game2048(){
		this.setTitle("Java 2048"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//設定點擊關閉按鈕時完全結束程式並釋放記憶體
		this.setResizable(false);//禁止手動調整視窗大小
		
		
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(()->new Game2048());
		//安全的UI執行續中啟動並建立遊戲視窗
	}
	
}
//定義核心遊戲畫布類別，繼承JPanel 並實作監聽器
class GamePanel extends JPanel implements ActionListener{
	private static final int SCREEN_SIZE=500;//遊戲總高500像素
	private static final int GRID_SIZE=4;//遊戲矩陣為4x4的網格結構
	private static final int TILE_SIZE=100;//定義每個數字方塊寬高維100像素
	private static final int GAP_SIZE =16;//定義方塊與方塊間的間隔為16像素
	
	private enum GameState{//宣告列舉型態，用來定義遊戲經歷的三種狀態
		START,RUNNING,GAME_OVER		
	}
	private GameState state= GameState.START; 
	//宣告一個變數紀錄當前狀態，預設是START
	
	private final int[][] board = new int[GRID_SIZE][GRID_SIZE];
	//建立4x4二維陣列作為遊戲的盤面(0代表空位)
	
	private int score=0;//遊戲分數初始為0
	private boolean gameWon=false;//勝利狀態初始化為false
	private final Random random = new Random(); //實體化亂數工具
	private Time time; //計時器物件，用於刷新遊戲畫面
	private Clip bgmClip; //背景音樂控制物件，可啟動或關閉音樂
	
	private final Image titleBg = new ImageIcon("title_bg.png").getImage();
	//啟動時，載入封面背景圖
	
	
}
