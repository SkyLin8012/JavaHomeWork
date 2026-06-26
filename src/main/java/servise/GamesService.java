package servise;

import java.util.List;

import model.entity.Games;

public interface GamesService {
	//create
		//註冊,新增
		void createGame(Games games);
	//read
		//搜尋遊戲以遊戲關鍵字
		List<Games> findGameByName(String name);		
		//搜尋全部
		List<Games> findAllGames();
	//update
		void update(Games game);
	//delete
		void deleteByid(int id);
}
