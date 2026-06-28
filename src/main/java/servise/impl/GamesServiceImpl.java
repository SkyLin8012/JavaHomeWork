package servise.impl;

import java.awt.Image;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import dao.impl.GamesDaoImpl;
import model.entity.Games;
import servise.GamesService;

public class GamesServiceImpl implements GamesService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	GamesDaoImpl ga = new GamesDaoImpl();
	@Override
	public void createGame(Games games) {
		ga.insert(games);		
	}
	@Override
	public Games findGameByid(int id) {
		// TODO Auto-generated method stub
		return ga.getByid(id);
	}

	@Override
	public List<Games> findGameByName(String name) {
		
		return ga.getByname(name);
	}

	@Override
	public List<Games> findAllGames() {
		
		return ga.getGamesAll();
	}

	@Override
	public void update(Games game) {
		ga.updateGames(game);
		
	}

	@Override
	public void deleteByid(int id) {
		ga.deletGames(id);
		
	}

	@Override
	public DefaultTableModel findAllGamesTable() {
		List<Games> games = ga.getGamesAll();
		DefaultTableModel model = new DefaultTableModel(){
			
			@Override
			public Class<?> getColumnClass(int columnIndex)
			{
				if(columnIndex==1) {
					return javax.swing.Icon.class;
				}
				return super.getColumnClass(columnIndex);
			}
			@Override
			public boolean isCellEditable(int row,int column) {
				return column==6;//|| column==7|| column==8;
			}	
		};
		
		model.addColumn("編號");
		model.addColumn("圖片");
		model.addColumn("名稱");
		model.addColumn("說明");
		model.addColumn("時間");
		model.addColumn("是否安裝");
		model.addColumn("執行遊戲");
		//model.addColumn("修改");
		//model.addColumn("刪除");
		
		for(Games g: games){
			ImageIcon imageIcon = null;
		    try {
			        
			        java.net.URL imgURL = getClass().getResource("/controller/img/"+g.getPicture());
			        
			        if (imgURL != null) {
			            ImageIcon img1 = new ImageIcon(imgURL);
			            java.awt.Image t1 = img1.getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
			            imageIcon = new ImageIcon(t1);
			        } else {
			            System.out.println("錯誤：找不到圖片路徑！");
			        }
			}catch(Exception e)
			{
				imageIcon=null;
			}
			String ins = g.getInstall().equals("Y")? "已安裝":"未實裝";
			
			Object[] row= new Object[] {
				g.getId(),imageIcon,g.getName(),
				g.getIntroduce(),
				g.getTimes(),ins,
				"執行"//, 
				//"修改",
				//"刪除"
			};
			model.addRow(row);
		}
		return model;
	}



}
