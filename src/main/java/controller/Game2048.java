package controller;

import java.awt.Color;//顏色類別，設定方塊及背景的RBG顏色
import java.awt.Dimension;//尺寸類別，封裝畫布的寬度與高度像素
import java.awt.Image;//影像基礎類別，儲存讀入的圖片資料
import java.awt.event.ActionEvent;//事件類別，用以加收定時器刷新的訊號
import java.awt.event.ActionListener;//動作監聽器介面，用於接收並處理ActionEvent
import java.awt.event.KeyAdapter; //鍵盤適配器，用以實作鍵盤介面，挑選需要複寫即可
import java.sql.Time;//計時工具
import java.util.ArrayList; //動態陣列集合，用以暫存盤面的空網格
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
		
		GamePanel panel = new GamePanel();
		//實體化核心遊戲畫布
		this.add(panel);//將核心畫布裝入視窗框架
		this.pack();//自動調適外框用以適配畫布面板
		
		this.setLocationRelativeTo(null);//遊戲初始於畫面中央顯示
		this.setVisible(true);//將視窗設為可見，顯示遊戲畫面
		
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
	private boolean gameOver=false; //遊戲失敗狀態標記為false
	private boolean gameWon=false;//勝利狀態初始化為false
	private final Random random = new Random(); //實體化亂數工具
	private Time time; //計時器物件，用於刷新遊戲畫面
	private Clip bgmClip; //背景音樂控制物件，可啟動或關閉音樂
	
	private final Image titleBg = new ImageIcon("title_bg.png").getImage();
	//啟動時，載入封面背景圖
	
	public GamePanel() {//建構子，初始化畫布屬性
		//設定此面板的尺寸500*500像素
		this.setPreferredSize(new Dimension(SCREEN_SIZE,SCREEN_SIZE));
		//畫布的背景顏色(經典2048土黃)
		this.setBackground(new Color(0xBBDA0));
		//允許此面板獲取鍵盤焦點，用以接收玩家鍵盤輸入
		this.setFocusable(true);
		//掛載自訂的鍵盤監聽器，監控玩家操作
		this.addKeyListener(new MyKeyAdapter());
		initGame();//配置第一局遊戲盤面
	}
	//新局初始化方法
	private void initGame() {
		score=0; //遊戲分數歸零
		gameOver=false;//重設遊戲狀態:未失敗
		gameWon = false;//重設遊戲狀態:未勝利
		for(int r=0; r<GRID_SIZE;r++) //外迴圈遍歷盤面每一行
		{
		 for(int c=0; c< GRID_SIZE;c++)//內迴圈遍歷盤面每一列 
		 {
			 board[r][c]=0;//將所有16個網格值通通清空為0
		 }
		}
		spawnTile();//隨機投放第一個初始方塊
		spawnTile();//隨機投放第二個初始方塊
		
	}
	//隨機生成新方塊
	private void spawnTile() {
		ArrayList<int[]> emptyTiles = new ArrayList<>();
		//建立動態陣列，用來儲存所有當前為0的格子座標
		for(int r=0;r<GRID_SIZE;r++)//橫向掃描二維陣列
		{
			for(int c=0;c<GRID_SIZE;c++)//縱向掃描二維陣列
			{
				if(board[r][c]==0)//若該位置的值為0(代表空位)
				{
					emptyTiles.add(new int[] {r,c});
					//將該空位的列索引與行索引以陣列型式存入集合中
				}
			}
		}
		if(!emptyTiles.isEmpty()) {//如果盤面上還有剩餘空格
			int[] tile =emptyTiles.get(random.nextInt(emptyTiles.size()));
			//從所有空位名單中隨機抽中一個位置
			board[tile[0]][tile[1]]=(random.nextInt(10)==0)?4:2;
			//有10%的機率生成數字4，90%機率生成數字2	
		}
	}
	//核心算法:向左滑動與合併邏輯
	private boolean moveLeft() {
		boolean moved = false; //【本輪是否移動】標記為false
		boolean mergedInThisTurn=false;//【本輪是否方生方塊合併】標記為false
		for(int r=0;r<GRID_SIZE;r++) //逐行處理4個橫列
		{
			int[]row=board[r]; //取出當前整行的陣列數據
			int[]newRow=new int[GRID_SIZE];
			//建立長度為4的暫存陣列，用來存放靠左對齊後的新數據
			int index=0;//設定暫存陣列的填入索引指標
			
			for(int c=0;c<GRID_SIZE;c++) //第一步:排除所有0，把有數字方塊通通
			{
			 if(newRow[c]!=0&& newRow[c]==newRow[c+1])//若目前格子有數字，且與右手邊相鄰的格子數值完全相同
			 {
				newRow[c]*=2;//目前格子的數值翻倍(完全合併)
				score+= newRow[c];//將合併後產生的新數值加進玩家的總分中
				mergedInThisTurn = true;//標記本輪發生合併
				if(newRow[c]==2048) {//關鍵判定
					gameWon=true; //若合併出2048直接標記勝利狀態為true
				}
				for(int i = c+1;i<GRID_SIZE-1;i++)//合併後，後方所有數字必須往前遞補一格
				{
					newRow[i]=newRow[i+1];//後方格子集體向前挪動
				}
				newRow[GRID_SIZE-1]=0;//挪動完畢後，最後尾端一定會空出一個網格，直接補0
			 }
			}
			for(int c=0;c<GRID_SIZE;c++)//第三步:將計算好的新整行結果，寫回原來的遊戲盤面
			{
				if(board[r][c]!=newRow[c]) {//若原盤面數據與合併後數據不一致
					moved=true;//位移狀態為true
				}
				board[r][c]=newRow[c];//將新數據正是覆蓋回主盤面陣列中
			}
		}
		if(mergedInThisTurn) {//任何一對數字合併成功，播放合併音效
			playSound("merge.wav");
		}
		return moved;//告訴外層本輪滑動是否產生變化
	}
	private void playSound(String string) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private class MyKeyAdapter extends KeyAdapter{
		
	}
	
	
}
