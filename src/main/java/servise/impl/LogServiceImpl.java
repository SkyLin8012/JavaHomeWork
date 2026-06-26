package servise.impl;

import java.util.List;
import java.util.stream.Collectors;

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

}
