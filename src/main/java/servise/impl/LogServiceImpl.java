package servise.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import dao.impl.LogDaoImpl;
import model.entity.Log;
import servise.LogService;

public class LogServiceImpl implements LogService{

	public static void main(String[] args) {
		//List<Log> logs = new LogServiceImpl().findLogByGame("貪");
		List<Log> logs = new LogServiceImpl().findLogByMember("貓");
		for(Log o: logs)
		{
			System.out.println(o.getMemberName()+" "+ o.getGameName()+" "+o.getTopScore());
		}
	}
	LogDaoImpl lg = new LogDaoImpl();
	@Override
	public void createLog(Log log) {
		lg.insert(log);		
	}

	@Override
	public List<Log> findLogByGame(String name) {		
		return lg.getByGameName(name).stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore()) -Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Log> findLogByMember(String name) {
		
		return lg.getByMemberName(name).stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore()) -Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Log> findAllLogs() {		
		return lg.getLogAll().stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore())-Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
	}

	@Override
	public void update(String TopScore, String times, int id) {		
		lg.updateLog(TopScore, times, id);
	}

	@Override
	public void deleteByUid(int id) {		
		lg.deletLog(id);
	}

	@Override
	public String PersonAchievements(String name) {
		String show="";
		List<Log> arraylogs =lg.getByMemberName(name).stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore()) -Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
		//找最高分的一筆
		Log firslog = arraylogs.get(0);
		
		show+= firslog.getMemberName()+"您最高分:"+
				  
				  "\n遊戲名稱:"+firslog.getGameName()+"\t"+
				  "\t分數:"+firslog.getTopScore()+
				  "\t日期:"+firslog.getTimes()+"\n";
		show+="\n";
		//找最後紀錄一筆
		Log lastlog =arraylogs.stream()
				.sorted((o1,o2)->(o2.getId()) -(o1.getId()))
				.collect(Collectors.toList()).get(0);
		show+= lastlog.getMemberName()+"您最後遊完的紀錄"+
				  
				  "\n遊戲名稱:"+lastlog.getGameName()+"\t"+
				  "\t分數:"+lastlog.getTopScore()+
				  "\t日期:"+lastlog.getTimes()+"\n";
		show+="\n";
	    show+=lastlog.getMemberName()+"個人遊戲紀錄:"+"\n";
		if(arraylogs.size()>0)
	    {
	    	for(Log o:arraylogs)
	    	{
	    		show+="遊戲名稱:"+o.getGameName()+"\t"+
	  				  "\t分數:"+o.getTopScore()+
	  				  "\t日期:"+o.getTimes()+"\n";
	    	}
	    }
		
		return show;
	}

	@Override
	public DefaultTableModel findLogsTable() {
		List<Log> logs = lg.getLogAll().stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore())-Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
		DefaultTableModel model = new DefaultTableModel();
		//建立欄位
		model.addColumn("遊戲名稱");
		model.addColumn("玩家ID");
		model.addColumn("玩家名稱");
		model.addColumn("分數");
		model.addColumn("日期");
		
		for(Log l: logs)
		{
			//將每一筆資料建立一維陣列
			Object[] row = new Object[] {
				l.getGameName(),
				l.getMemberid(),
				l.getMemberName(),
				l.getTopScore(),
				l.getTimes()
			};
			model.addRow(row);
		}
		return model;
	}

	@Override
	public DefaultTableModel findLogsTableByGName(String name) {
		List<Log> logs =lg.getByGameName(name).stream()
				.sorted((o1,o2)->Integer.parseInt(o2.getTopScore()) -Integer.parseInt(o1.getTopScore()))
				.collect(Collectors.toList());
		DefaultTableModel model = new DefaultTableModel();
		//建立欄位
		model.addColumn("遊戲名稱");
		model.addColumn("玩家ID");
		model.addColumn("玩家名稱");
		model.addColumn("分數");
		model.addColumn("日期");
		
		for(Log l: logs)
		{
			//將每一筆資料建立一維陣列
			Object[] row = new Object[] {
				l.getGameName(),
				l.getMemberid(),
				l.getMemberName(),
				l.getTopScore(),
				l.getTimes()
			};
			model.addRow(row);
		}
		return model;
	}

}
