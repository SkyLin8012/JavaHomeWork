package servise;

import java.util.List;

import model.entity.Log;

public interface LogService {
	//create
		//註冊,新增
		void createLog(Log log);
	//read
		//特定遊戲關鍵字查紀錄
		List<Log> findLogByGame(String name);
		//搜尋會員名稱查紀錄
		List<Log> findLogByMember(String name);
		//搜尋全部紀錄
		List<Log> findAllLogs();
	//update
		//修改紀錄
		void update(String TopScore,String times,int id);
	//delete
		//刪除紀錄
		void deleteByUid(int id);
}
