package dao;

import java.util.List;

import model.entity.Log;

public interface LogDao {
	//create
	void insert(Log log);
	//read	
	List<Log> getByGameName(String name);
	List<Log> getByMemberName(String name);
	List<Log> getLogAll();
	//update
	void updateLog(String TopScore,String times,int id);
	
	//delete
	void deletLog(int id);
}
